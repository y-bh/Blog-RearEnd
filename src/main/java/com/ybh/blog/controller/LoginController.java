package com.ybh.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.ybh.blog.DTO.UserDTO;
import com.ybh.blog.VO.JwtUserVO;
import com.ybh.blog.VO.Result;
import com.ybh.blog.contants.TokenContants;
import com.ybh.blog.service.UserService;
import com.ybh.blog.utils.JwtUtil;
import com.ybh.blog.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author:za-yubohan
 * @Date:2022/8/30 11:33
 */
@RestController
@RequestMapping("/blog")
@Slf4j
public class LoginController {

    @Resource
    JwtUtil jwtUtil;
    @Resource
    RedisUtil redisUtil;
    @Resource
    UserService userService;

    /**
     * @description: 注册用户
     * @author: Altria-LS
     **/
    @RequestMapping("/register")
    public Result<String> register(@RequestBody JwtUserVO jwtUserVO) {
        String token = null;
        try {
            //1.根据用户信息生成token
            token = jwtUtil.generalToken(jwtUserVO);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        //2.将token存入redis中
        redisUtil.setValue(TokenContants.JWT_ID+jwtUserVO.getAccountId(),token,1L);
        //3.保存用户信息
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(jwtUserVO, userDTO);
        Boolean flag = userService.saveUserInfo(userDTO);
        if (flag) {
            System.out.println(token);
            return Result.ok(token);
        } else {
            return Result.error("注册失败");
        }
    }

    /**
     * @description: 登录用户
     * @author: Altria-LS
     **/
    @RequestMapping("/login")
    public Result<?> login(@RequestParam String accountId, @RequestParam String password) {
        // 判断输错次数是否到达需要验证码的次数
        String picErrorKey = TokenContants.LOGIN_PWD_ERROR_KEY + accountId;
        if (pwdPicIsNeed(picErrorKey)) {
            log.warn("用户登录失败，安全验证！");
            return Result.error(TokenContants.LOGIN_CAPTCHA_IS_NULL, "安全验证");
        }
        //判断是否冻结用户，超过5次冻结用户
        String freezeKey = TokenContants.JWT_FREEZE_COUT + accountId;
        //校验用户是否需要冻结，如果需要冻结，则冻结用户登录5分钟
        Result<?> result = validateAndFreezeUser(freezeKey, accountId);
        if (result!=null){
            return result;
        }
        UserDTO userDTO = userService.verifyUserInfo(accountId, password);
        if (userDTO == null) {
            //错误次数记录+1
            restPwdErrorCount(picErrorKey,freezeKey);
            return Result.error("账号或密码不正确");
        } else {
            JwtUserVO jwtUserVO = new JwtUserVO();
            BeanUtil.copyProperties(userDTO, jwtUserVO);
            String token = null;
            try {
                token = jwtUtil.generalToken(jwtUserVO);
            } catch (Exception e) {
                return Result.error(e.getMessage());
            }
            //登陆成功删除校验信息
            redisUtil.delete(freezeKey);
            redisUtil.delete(picErrorKey);
            //保存token到redis
            redisUtil.set(TokenContants.JWT_ID+accountId,token);
            redisUtil.setValue(TokenContants.JWT_LOGIN_USER_INFO+accountId,jwtUserVO,1L);

            return Result.ok(token);
        }

    }

    /**
     * @Description: 登出操作
     * @Author: za-yubohan
     **/
    public Result<?> loginOut(HttpServletRequest httpRequest) {
        String token = httpRequest.getHeader(TokenContants.JWT_TOKEN_KEY);
        JwtUserVO userVO = jwtUtil.parseToken();
        redisUtil.delete(TokenContants.JWT_ID+userVO.getAccountId());
        return Result.ok();
    }


    /**
     * @Description: 从redis中获取密码校验失败次数, 判断是否需要图片验证码
     * @Author: za-yubohan
     **/
    boolean pwdPicIsNeed(String key) {
        try {
            //获取当前用户获取密码失败次数
            String erroeCount = redisUtil.get(key);
            if (StrUtil.isBlank(erroeCount)) {
                return false;
            }
            if (Integer.parseInt(erroeCount) >= TokenContants.PWD_PIC_LOCKED_COUNT_DEFAULT) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.warn("从redis中获取密码校验失败次数异常 e = {}", e);
        }
        return true;
    }

    /**
     * @Description: 校验用户是否需要冻结，如果需要冻结， 则冻结用户5分钟
     * @Author: za-yubohan
     **/
    private Result<?> validateAndFreezeUser(String freezeKey, String userName) {
        //判断用户冻结是否超过5分钟的redis key
        String timeoutKey = TokenContants.LOGIN_USER_FREEZE_TIMEOUT_KEY + userName;
        if (isNeedFreeze(freezeKey)) {
            //密码错误超时时间5分钟
            redisUtil.set(timeoutKey, userName, TokenContants.PWD_FREEZE_USER_SECONDS);
            //冻结用户次数清0
            redisUtil.delete(freezeKey);
            log.warn("用户登录密码错误次数超过5次,用户名={}", userName);
            return Result.error("密码输入次数过多，请五分钟后再试");
        }
        return null;
    }

    /**
     * @Description: 从redis中获取密码校验失败次数,判断是否需要冻结用户
     * @Author: za-yubohan
     **/
    private boolean isNeedFreeze(String key) {
        try {
            String errorTotal = redisUtil.get(key);

            //为空未锁定
            if (StrUtil.isBlank(errorTotal)) {
                return false;
            }
            //如果失败次数大于等于限制 则视需要图片验证码
            if (Integer.parseInt(errorTotal) >= TokenContants.PWD_FREEZE_USER_COUNT_DEFAULT) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.warn("从redis中获取密码校验失败次数异常 e = {}", e);
            e.printStackTrace();
        }
        return true;
    }

    /**
     * @Description: 从redis中重置密码校验失败次数
     * @Author: za-yubohan
     **/
    private void restPwdErrorCount(String picErrorKey, String freezeKey) {
        try {
            Integer picErrorCount;
            Integer freezeErrorCount;
            //1.从redis中获取密码失败的次数
            String picErrorTotal = redisUtil.get(picErrorKey);
            String freezeErrorTotal = redisUtil.get(freezeKey);

            picErrorCount = StrUtil.isBlank(picErrorTotal) ? 0 : Integer.parseInt(picErrorTotal);
            freezeErrorCount = StrUtil.isBlank(freezeErrorTotal) ? 0 : Integer.parseInt(freezeErrorTotal);

            picErrorCount++;
            freezeErrorCount++;
            redisUtil.set(picErrorKey, picErrorCount.toString());
            redisUtil.set(freezeKey, freezeErrorCount.toString());
        } catch (Exception e) {
            log.warn("从redis中重置密码校验失败次数 异常 e = {}", e);
        }
    }

}

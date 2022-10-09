package com.ybh.blog.controller;

import com.ybh.blog.VO.Result;
import com.ybh.blog.VO.UserVO;
import com.ybh.blog.contants.TokenContants;
import com.ybh.blog.service.UserService;
import com.ybh.blog.utils.JwtUtil;
import com.ybh.blog.utils.RedisUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
@RestController
@RequestMapping("/blog/user")
public class UserController {
    @Resource
    JwtUtil jwtUtil;
    @Resource
    RedisUtil redisUtil;
    @Resource
    UserService userService;

    /**
     * @description: 查询用户信息
     * @author: Altria-LS
     **/
    @RequestMapping("/getUserInfo")
    public Result<UserVO> getUserInfo() {
        UserVO userVO = null;
        try {
            UserVO userInfo = jwtUtil.parseToken();
            userVO = (UserVO) redisUtil.getValue(TokenContants.JWT_LOGIN_USER_INFO + userInfo.getAccountId(), UserVO.class);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        UserVO userResult = userService.getUserInfo(userVO);
        if (userResult != null) {
            return Result.ok(userResult);
        } else {
            return Result.error("未查询到用户信息");
        }
    }

}

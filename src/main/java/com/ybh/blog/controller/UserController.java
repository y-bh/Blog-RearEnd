package com.ybh.blog.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.ybh.blog.DTO.UserDTO;
import com.ybh.blog.Enum.PlatformCodeEnum;
import com.ybh.blog.VO.JwtUserVO;
import com.ybh.blog.VO.Result;
import com.ybh.blog.service.UserService;
import com.ybh.blog.utils.JwtUtil;
import com.ybh.blog.utils.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
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
    public Result<JwtUserVO> getUserInfo() {
        JwtUserVO jwtUserVO = null;
        try {
            jwtUserVO = jwtUtil.parseToken();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        JwtUserVO userInfo = userService.getUserInfo(jwtUserVO);
        if (userInfo!=null){
            return Result.ok(userInfo);
        }else{
            return Result.error("未查询到用户信息");
        }
    }

}

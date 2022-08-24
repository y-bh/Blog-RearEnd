package com.ybh.blog.controller;

import cn.hutool.core.util.StrUtil;
import com.ybh.blog.DTO.UserDTO;
import com.ybh.blog.VO.JwtUserVO;
import com.ybh.blog.VO.Result;
import com.ybh.blog.service.UserService;
import com.ybh.blog.utils.JwtUtil;
import com.ybh.blog.utils.RedisUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
     * @description: 登录获取token
     * @author: Altria-LS
     **/
    @RequestMapping("/login")
    public String login(@RequestBody JwtUserVO jwtUserVO){
        //1.根据用户信息生成token
        String token = jwtUtil.generalToken(jwtUserVO);
        //2.将token存入redis中
        redisUtil.setValue(token,jwtUserVO,1L);
        return token;
    }

    /**
     * @description: 查询用户信息
     * @author: Altria-LS
     **/
    @RequestMapping("/getUserInfo")
    public Result<UserDTO> getUserInfo(){
        JwtUserVO jwtUserVO = jwtUtil.parseToken();
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(jwtUserVO, userDTO);
        UserDTO userInfo = userService.getUserInfo(userDTO);
        if (userInfo!=null){
            return Result.ok(userInfo);
        }else{
            return Result.error(null);
        }
    }

}

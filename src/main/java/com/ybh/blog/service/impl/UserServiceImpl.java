package com.ybh.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ybh.blog.DTO.UserDTO;
import com.ybh.blog.VO.JwtUserVO;
import com.ybh.blog.manager.UserManager;
import com.ybh.blog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserManager userManager;

    @Override
    public JwtUserVO getUserInfo(JwtUserVO jwtUserVO) {
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(jwtUserVO, userDTO);
        UserDTO user = userManager.getUserByAccountId(userDTO);
        if (user==null){
            return null;
        }
        BeanUtils.copyProperties(userDTO,jwtUserVO);
        return jwtUserVO;
    }

    @Override
    public Boolean saveUserInfo(JwtUserVO jwtUserVO){
        UserDTO userDTO = new UserDTO();
        BeanUtil.copyProperties(jwtUserVO, userDTO);
        return userManager.saveUserInfo(userDTO);
    }

    @Override
    public JwtUserVO verifyUserInfo(String accountId,String password) {
        JwtUserVO userVO = new JwtUserVO();
        UserDTO userDTO = userManager.verifyUserInfo(accountId, password);
        BeanUtil.copyProperties(userDTO,userVO);
        return userVO;
    }
}

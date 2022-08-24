package com.ybh.blog.service.impl;

import com.ybh.blog.DO.UserDO;
import com.ybh.blog.DTO.UserDTO;
import com.ybh.blog.mapper.UserMapper;
import com.ybh.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserDTO getUserInfo(UserDTO userDTO) {
        UserDTO data = new UserDTO();
        UserDO userDO = userMapper.selectById(1001);
        if (userDO==null){
            return null;
        }
        BeanUtils.copyProperties(userDO,data);
        return data;
    }
}

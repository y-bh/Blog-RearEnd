package com.ybh.blog.manager.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybh.blog.DO.UserDO;
import com.ybh.blog.DTO.UserDTO;
import com.ybh.blog.convert.UserConvertMapper;
import com.ybh.blog.manager.UserManager;
import com.ybh.blog.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author:za-yubohan
 * @Date:2022/9/1 15:38
 */
@Service
public class UserManagerImpl extends ServiceImpl<UserMapper, UserDO> implements UserManager {
    @Resource
    UserMapper userMapper;

    /**
     * @Description: 获取用户信息
     * @Author: Altria-LS
     **/
    @Override
    public UserDTO getUserByAccountId(UserDTO userDTO){
        QueryWrapper<UserDO> userDTOQueryWrapper = new QueryWrapper<>();
        UserDO userDO = userMapper.selectOne(userDTOQueryWrapper.eq("account_id",userDTO.getAccountId()));
        if (userDO==null){
            return null;
        }
        return UserConvertMapper.INSTANCE.userDOtoUserDTO(userDO);
    }

    /**
     * @Description: 保存用户信息
     * @Author: Altria-LS
     **/
    @Override
    public Boolean saveUserInfo(UserDTO userDTO){
        UserDO userDO = UserConvertMapper.INSTANCE.userDTOtoUserDO(userDTO);
        int i = userMapper.insert(userDO);
        if (i!=0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * @Description: 校验用户账号密码是否正确
     * @Author: Altria-LS
     **/
    @Override
    public UserDTO verifyUserInfo(String accountId, String password){
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        UserDO userDO = userMapper.selectOne(wrapper.eq("account_id",accountId));
        UserDTO userDTO = UserConvertMapper.INSTANCE.userDOtoUserDTO(userDO);
        if (StrUtil.equals(password,userDTO.getPassword())){
            return userDTO;
        }else {
            return null;
        }
    }
}

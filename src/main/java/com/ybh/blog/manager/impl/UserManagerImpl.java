package com.ybh.blog.manager.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybh.blog.DO.UserDO;
import com.ybh.blog.DTO.UserDTO;
import com.ybh.blog.manager.UserManager;
import com.ybh.blog.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
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
    public UserDTO getUserByAccountId(UserDTO userDTO){
        QueryWrapper<UserDO> userDTOQueryWrapper = new QueryWrapper<>();
        UserDO userDO = userMapper.selectOne(userDTOQueryWrapper.eq("account_id",userDTO.getAccountId()));
        if (userDO==null){
            return null;
        }
        BeanUtils.copyProperties(userDO,userDTO);
        return userDTO;
    }

    /**
     * @Description: 保存用户信息
     * @Author: Altria-LS
     **/
    public Boolean saveUserInfo(UserDTO userDTO){
        UserDO userDO = new UserDO();
        BeanUtil.copyProperties(userDTO,userDO);
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
    public UserDTO verifyUserInfo(String accountId,String password){
        UserDTO userDTO = new UserDTO();
        QueryWrapper<UserDO> wrapper = new QueryWrapper<>();
        UserDO userDO = userMapper.selectOne(wrapper.eq("account_id",accountId));
        BeanUtil.copyProperties(userDO,userDTO);
        if (StrUtil.equals(password,userDTO.getPassword())){
            return userDTO;
        }else {
            return null;
        }
    }
}

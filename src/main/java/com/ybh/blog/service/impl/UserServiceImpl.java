package com.ybh.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ybh.blog.DO.UserDO;
import com.ybh.blog.DTO.UserDTO;
import com.ybh.blog.VO.Result;
import com.ybh.blog.mapper.UserMapper;
import com.ybh.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybh.blog.utils.RedisUtil;
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
        QueryWrapper<UserDO> userDTOQueryWrapper = new QueryWrapper<>();
        UserDO userDO = userMapper.selectOne(userDTOQueryWrapper.eq("account_id",userDTO.getAccountId()));
        if (userDO==null){
            return null;
        }
        BeanUtils.copyProperties(userDO,userDTO);
        return userDTO;

    }

    @Override
    public Boolean saveUserInfo(UserDTO userDTO) throws Exception {
        UserDO userDO = new UserDO();
        BeanUtil.copyProperties(userDTO,userDO);
        int i = userMapper.insert(userDO);
        if (i!=0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public UserDTO verifyUserInfo(String accountId,String password) {
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

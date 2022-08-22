package com.ybh.blog.service.impl;

import com.ybh.blog.entity.User;
import com.ybh.blog.mapper.UserMapper;
import com.ybh.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

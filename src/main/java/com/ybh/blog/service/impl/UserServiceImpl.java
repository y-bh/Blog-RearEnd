package com.ybh.blog.service.impl;

import com.ybh.blog.DTO.UserDTO;
import com.ybh.blog.Enum.PlatformCodeEnum;
import com.ybh.blog.VO.UserVO;
import com.ybh.blog.contants.TokenContants;
import com.ybh.blog.convert.UserConvertMapper;
import com.ybh.blog.exception.BaseException;
import com.ybh.blog.manager.UserManager;
import com.ybh.blog.service.UserService;
import com.ybh.blog.utils.JwtUtil;
import com.ybh.blog.utils.RedisUtil;
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
    @Resource
    JwtUtil jwtUtil;
    @Resource
    RedisUtil redisUtil;

    @Override
    public UserVO getUserInfo(UserVO userVO) {
        UserDTO userDTO = UserConvertMapper.INSTANCE.userVOtoUserDTO(userVO);
        UserDTO user = userManager.getUserByAccountId(userDTO);
        if (user==null){
            return null;
        }
        UserVO result = UserConvertMapper.INSTANCE.userDTOtoUserVO(user);
        return result;
    }

    @Override
    public void saveUserInfo(UserVO userVO){
        UserDTO userDTO = UserConvertMapper.INSTANCE.userVOtoUserDTO(userVO);
        Boolean flag = userManager.saveUserInfo(userDTO);
        if (flag) {
            //根据用户信息生成token
            String token = jwtUtil.generalToken(userVO);
            //将token存入redis中
            redisUtil.set(TokenContants.JWT_ID + userVO.getAccountId(), token, 1L);
        } else {
            throw new BaseException(PlatformCodeEnum.SAVE_ERROR.getValue());
        }

    }

    @Override
    public UserVO verifyUserInfo(String accountId,String password) {
        UserDTO userDTO = userManager.verifyUserInfo(accountId, password);
        return UserConvertMapper.INSTANCE.userDTOtoUserVO(userDTO);
    }
}

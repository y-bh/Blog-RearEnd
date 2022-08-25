package com.ybh.blog.service;

import com.ybh.blog.DO.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybh.blog.DTO.UserDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
public interface UserService extends IService<UserDO> {
    /**
     * @description: 查询用户信息
     * @author: Altria-LS
     **/
    public UserDTO getUserInfo(UserDTO userDTO);

    /**
     * @return
     * @description: 保存用户信息
     * @author: Altria-LS
     */
    public Boolean saveUserInfo(UserDTO userDTO);

    /**
     * @description: 验证用户信息
     * @author: Altria-LS
     **/
    public UserDTO verifyUserInfo(String accountId,String password);

}

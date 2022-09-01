package com.ybh.blog.manager;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ybh.blog.DO.UserDO;
import com.ybh.blog.DTO.UserDTO;

/**
 * @Author:za-yubohan
 * @Date:2022/9/1 15:38
 */
public interface UserManager extends IService<UserDO> {
    /**
     * @description: 查询用户信息
     * @author: Altria-LS
     **/
     UserDTO getUserByAccountId(UserDTO userDTO);

    /**
     * @return
     * @description: 保存用户信息
     * @author: Altria-LS
     */
     Boolean saveUserInfo(UserDTO userDTO);

    /**
     * @description: 验证用户信息
     * @author: Altria-LS
     **/
     UserDTO verifyUserInfo(String accountId,String password);
}

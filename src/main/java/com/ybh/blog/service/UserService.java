package com.ybh.blog.service;

import com.sun.org.apache.xalan.internal.xsltc.trax.DOM2TO;
import com.ybh.blog.DO.UserDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ybh.blog.DTO.UserDTO;
import com.ybh.blog.VO.Result;

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
    public UserDTO getUserInfo(UserDTO userDTO) throws Exception;

    /**
     * @return
     * @description: 保存用户信息
     * @author: Altria-LS
     */
    public Boolean saveUserInfo(UserDTO userDTO) throws Exception;

    /**
     * @description: 验证用户信息
     * @author: Altria-LS
     **/
    public UserDTO verifyUserInfo(String accountId,String password);

}

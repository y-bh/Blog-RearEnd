package com.ybh.blog.service;

import com.ybh.blog.VO.UserVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Altria
 * @since 2022-08-23
 */
public interface UserService {
    /**
     * @description: 查询用户信息
     * @author: Altria-LS
     **/
    UserVO getUserInfo(UserVO userVO);

    /**
     * @return
     * @description: 保存用户信息
     * @author: Altria-LS
     */
    void saveUserInfo(UserVO jwtUserVO);

    /**
     * @description: 验证用户信息
     * @author: Altria-LS
     **/
    UserVO verifyUserInfo(String accountId,String password);

}

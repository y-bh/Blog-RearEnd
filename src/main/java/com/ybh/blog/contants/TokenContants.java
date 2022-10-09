package com.ybh.blog.contants;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:za-yubohan
 * @Date:2022/8/30 14:50
 */
@Component
public class TokenContants {
    public static final String JWT_TOKEN_KEY = "token";
    public static final String JWT_LOGIN_USER_INFO = "JWT_LOGIN_USER_INFO_";
    public static final String JWT_FREEZE_COUT = "JWT_FREEZE_COUT_";
    public static final String LOGIN_PWD_ERROR_KEY = "LOGIN_PWD_ERROR_KEY_";
    public static Integer PWD_PIC_LOCKED_COUNT_DEFAULT = 5;
    public static final String LOGIN_USER_FREEZE_TIMEOUT_KEY = "LOGIN_USER_FREEZE_TIMEOUT_KEY_";
    public static Integer PWD_FREEZE_USER_SECONDS = 5 * 60;
    public static final Integer PWD_FREEZE_USER_COUNT_DEFAULT = 5;
    public static final String JWT_ID = "JWT_ID_";
}

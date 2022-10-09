package com.ybh.blog.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ybh.blog.Enum.PlatformCodeEnum;
import com.ybh.blog.VO.UserVO;
import com.ybh.blog.contants.TokenContants;
import com.ybh.blog.exception.BaseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description: JWT工具类
 * @Author: Altria-LS
 * @CreateTime: 2022-08-24  20:55
 */
@Slf4j
@Component
public class JwtUtil {

    public static final String privateKey = "sdusnbcxwlcnusdiacnbwekiwnxcuisancb";
    private SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
    @Resource
    RedisUtil redisUtil;

    /**
     * @Description: 获取转换后的私钥对象
     * @Author: za-yubohan
     **/
    public SecretKey getSecretkey(){
        return Keys.hmacShaKeyFor(privateKey.getBytes());
    }


    /**
     * @description: 生成token
     * @author: Altria-LS
     **/
    public String generalToken(UserVO userVO) throws BaseException {
        if (userVO==null){
            throw new BaseException("获取UserVO对象失败，无法生成token");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        //生成新token和获取老token，如果老token存在，重新赋值，还原登录状态
        String token=null;
        String newToken = Jwts.builder()
                .setId(String.valueOf(userVO.getAccountId()))
                .setIssuedAt(userVO.getGmtCreated())
                .signWith(getSecretkey(),signatureAlgorithm)
                .claim("accountId",userVO.getAccountId())
                .claim("creater", userVO.getCreater())
                .setExpiration(calendar.getTime())
                .compact();
        String oldToken=redisUtil.get(TokenContants.JWT_ID+userVO.getAccountId());
        if (StrUtil.isNotBlank(oldToken)){
            token=oldToken;
        }else {
            token=newToken;
        }
        redisUtil.set(TokenContants.JWT_ID+userVO.getAccountId(),token,1L);
        return token;
    }

    /**
     * @description: 根据token获取用户信息
     * @author: Altria-LS
     **/
    public UserVO parseToken() throws BaseException {
        //获取request请求
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //从Header获取token
        String authorization = request.getHeader(TokenContants.JWT_TOKEN_KEY);
        if (StrUtil.isBlank(authorization)) {
            log.error("token未携带");
            throw new BaseException("诶tnnd，token没找到");
        } else {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSecretkey()).build().parseClaimsJws(authorization).getBody();
            UserVO userInfo = getUserInfo(claims);
            if (userInfo==null){
                throw new BaseException(PlatformCodeEnum.SC_FORBIDDEN.getValue());
            }
//            return JSON.parseObject(redisUtil.get((String) body.get("accountId")),UserVO.class);
            return userInfo;
        }
    }

    private UserVO getUserInfo(Claims claims) {
        String accountId = claims.get("accountId", String.class);
        Object value = redisUtil.getValue(TokenContants.JWT_LOGIN_USER_INFO + accountId, UserVO.class);
        return (UserVO) value;
    }
}

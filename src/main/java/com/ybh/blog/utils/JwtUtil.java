package com.ybh.blog.utils;

import cn.hutool.core.util.StrUtil;
import com.ybh.blog.VO.JwtUserVO;
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
import java.util.Calendar;

/**
 * @Description: JWT工具类
 * @Author: Altria-LS
 * @CreateTime: 2022-08-24  20:55
 */
@Slf4j
@Component
public class JwtUtil {

    public static final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Resource
    RedisUtil redisUtil;


    /**
     * @description: 生成token
     * @author: Altria-LS
     **/
    public String generalToken(JwtUserVO jwtUserVO) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(jwtUserVO.getGmtCreated());
        calendar.add(Calendar.DATE, 1);
        String token = Jwts.builder()
                .setId(String.valueOf(jwtUserVO.getId()))
                .setIssuedAt(jwtUserVO.getGmtCreated())
                .signWith(key)
                .claim("accountId",jwtUserVO.getAccountId())
                .claim("creater", jwtUserVO.getCreater())
                .setExpiration(calendar.getTime())
                .compact();
        redisUtil.setValue(token,jwtUserVO,1L);
        return token;
    }

    /**
     * @description: 根据token获取用户信息
     * @author: Altria-LS
     **/
    public JwtUserVO parseToken() {
        //获取request请求
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        //从Header获取token，没有则从cookies获取
        String authorization = request.getHeader("token");
        if (StrUtil.isEmpty(authorization)) {
            Cookie[] cookies = request.getCookies();
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (StrUtil.equals("token", cookie.getName())) {
                        try {
                            authorization = URLDecoder.decode(cookie.getValue(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            log.error("cookie中不存在token:{}", e.getMessage());
                        }
                        break;
                    }
                }
            }
        }
        if (StrUtil.isBlank(authorization)) {
            log.error("token未携带");
            return null;
        } else {
            return (JwtUserVO) redisUtil.getValue(authorization, JwtUserVO.class);
        }
    }
}

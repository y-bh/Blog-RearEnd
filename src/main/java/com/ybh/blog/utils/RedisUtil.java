package com.ybh.blog.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Description: redis工具类
 * @Author: Altria-LS
 * @CreateTime: 2022-08-24  20:55
 */
@Component
public class RedisUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * @description: 将值带入缓存
     * @author: Altria-LS
     **/
    public void set(String key, String value) {
        this.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * @Description: 将值带入缓存，设置存活时间
     * @Author: za-yubohan
     **/
    public void set(String key, String obj, long liveSeconds) {
        redisTemplate.opsForValue().set(key, obj, liveSeconds, TimeUnit.SECONDS);
    }

    /**
     * @description: 获取对应key的value
     * @author: Altria-LS
     **/
    public String get(String key) {
        Object result = this.redisTemplate.opsForValue().get(key);
        if (result == null) {
            return null;
        } else {
            return result instanceof String ? (String) this.redisTemplate.opsForValue().get(key) : JSON.toJSONString(result);
        }
    }


    /**
     * @description: 将值放入缓存--泛型
     * @author: Altria-LS
     **/
    public <T> void setValue(String key, T value, long liveTimes) {
        String saveObjectString = JSON.toJSONString(value);
        redisTemplate.opsForValue().set(key, saveObjectString, liveTimes, TimeUnit.DAYS);
    }

    /**
     * @description: 根据key和传入类型获取value值
     * @author: Altria-LS
     **/
    public <T> Object getValue(String key, Class<T> clazz) {
        String result = this.get(key);
        return StrUtil.isBlank(result) ? null : JSON.parseObject(result, clazz, Feature.OrderedField);
    }

    /**
     * @description: 根据key删除
     * @author: Altria-LS
     **/
    public void delete(String key) {
        this.redisTemplate.delete(key);
    }
}

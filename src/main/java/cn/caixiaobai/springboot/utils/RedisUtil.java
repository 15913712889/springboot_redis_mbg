package cn.caixiaobai.springboot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author: caixiaobai
 * @Date: 2020/11/24 11:14
 * @Version 1.0
 */
@Slf4j
@Component
public class RedisUtil {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * SET操作
     *
     * @param key   KEY
     * @param value VALUE
     * @return 是否成功
     */
    public boolean set(String key, String value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("redis set操作异常：" + e.getMessage());
            return false;
        }
    }

    /**
     * GET操作
     *
     * @param key KEY
     * @return VALUE
     */
    public Object get(String key) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            return o;
        } catch (Exception e) {
            log.error("redis get操作异常：" + e.getMessage());
            return null;
        }
    }
}

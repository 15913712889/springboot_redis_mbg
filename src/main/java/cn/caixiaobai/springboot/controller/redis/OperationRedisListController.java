package cn.caixiaobai.springboot.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author: caixiaobai
 * @Date: 2020/12/02 09:50
 */
public class OperationRedisListController {

    //注入RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;
}

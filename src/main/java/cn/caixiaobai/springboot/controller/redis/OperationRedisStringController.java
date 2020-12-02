package cn.caixiaobai.springboot.controller.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: caixiaobai
 * @Date: 2020/12/01 11:08
 */
@RestController
@RequestMapping(value = "/string")
public class OperationRedisStringController {

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "/getString01",method = RequestMethod.POST)
    public String demo01(){

        ValueOperations valueOperations = redisTemplate.opsForValue();
        //单条插入，设置值key为StringRedis_cxq，value为value01的String类型的数据
        valueOperations.set("StringRedis_cxq01","value01");

        return null;
    }

    @RequestMapping(value = "/getString02",method = RequestMethod.POST)
    public String demo02(){

        ValueOperations valueOperations = redisTemplate.opsForValue();
        //在插入的同时前面增加10个二进制数,具体作用不明
        valueOperations.set("StringRedis_cxq02","value02",10);

        return null;
    }

    @RequestMapping(value = "/getString03",method = RequestMethod.POST)
    public String demo03(){

        ValueOperations valueOperations = redisTemplate.opsForValue();
        //TimeUnit.DAYS          //天
        //TimeUnit.HOURS         //小时
        //TimeUnit.MINUTES       //分钟
        //TimeUnit.SECONDS       //秒
        //TimeUnit.MILLISECONDS  //毫秒
        //设置超时时间为10秒
        valueOperations.set("StringRedis_cxq03","value03",10, TimeUnit.SECONDS);

        return null;
    }

    @RequestMapping(value = "/getString04",method = RequestMethod.POST)
    public String demo04(){

        ValueOperations valueOperations = redisTemplate.opsForValue();
        //存在就返回false,不做修改，不存在就添加，返回true
        Boolean aBoolean = valueOperations.setIfAbsent("StringRedis_cxq04", "value044");

        return null;
    }

    //后面的改用测试类来实现，启动项目繁琐
}

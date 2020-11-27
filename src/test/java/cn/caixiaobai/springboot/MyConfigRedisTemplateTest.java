package cn.caixiaobai.springboot;




import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: caixiaobai
 * @Date: 2020/11/24 10:28
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)//这个是测试出现问题的解决，需要系写上自己的启动类名称
public class MyConfigRedisTemplateTest {

    //注入RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 操作String
     */
    @Test
    public void test1(){
        //获取操作String'类型的对象
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //存储数据
        try {
            valueOperations.set("user1","添哥");
            valueOperations.set("user2","小添哥哥");
            System.out.println("存储数据成功");
            //获取数据
            String user1 = (String)valueOperations.get("user1");
            String user2 = (String)valueOperations.get("user2");
            System.out.println(user1 + "==>" + user2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

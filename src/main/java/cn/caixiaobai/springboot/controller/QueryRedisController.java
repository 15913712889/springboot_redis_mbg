package cn.caixiaobai.springboot.controller;

import cn.caixiaobai.springboot.dto.Users;
import cn.caixiaobai.springboot.mapper.UserMapper;
import cn.caixiaobai.springboot.pojo.User;
import cn.caixiaobai.springboot.result.SystemResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @Author: caixiaobai
 * @Date: 2020/11/24 14:33
 * @Version 1.0
 */
@Controller
public class QueryRedisController {
    
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value = "/demo/queryRedis",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult Demo01(){

        //通过ValueOperations
        HashOperations hashOperations = redisTemplate.opsForHash();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        ListOperations listOperations = redisTemplate.opsForList();
        SetOperations setOperations = redisTemplate.opsForSet();
        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

//        User user = new User();
//        user.setId(3);
//        user.setName("菜小白");
//        user.setAge(18);
//
//        String userKey = "user4";
//        hashOperations.putIfAbsent("redis_cxq",userKey,user);

        //Object o = hashOperations.get("redis_cxq", "user");

       // List<User> u = userMapper.selectList(null);

       // hashOperations.putIfAbsent("user_up","up",u);

       // listOperations.set("list_redis",1,u.get(0).toString());

        Map<String,String> map=new HashMap<String,String>();
//        map.put("key1","value1");
//        map.put("key2","value2");
//        map.put("key3","value3");
//        map.put("key4","value4");
//        map.put("key5","value5");
//
//        hashOperations.putAll("map1",map);

        //hashOperations.put("map1","putKey1","putValue");

        //Boolean aBoolean = hashOperations.putIfAbsent("map1", "putKey3", "putValu3");

        //Long map1 = hashOperations.size("map1");

        //Cursor map1 = hashOperations.scan("map1", null);
        //keys()
//        Set map1 = hashOperations.keys("map1");
//        Set cs = hashOperations.keys("测试");

        //multiGet
//        List<Object> list = new ArrayList<>();
//        list.add("user");
//        List listcs = hashOperations.multiGet("redis_cxq", list);

        //get()方法
//        Object o = hashOperations.get("redis_cxq", "user3");

        //scan()方法
//        Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan("redis_cxq",ScanOptions.scanOptions().match("user").build());
//        //Cursor<Map.Entry<Object,Object>> cursor = redisTemplate.opsForHash().scan("redis_cxq",ScanOptions.NONE);
//        redisTemplate.opsForHash().scan("redis_cxq", ScanOptions.NONE);
//        while (cursor.hasNext()) {
//            Map.Entry<Object, Object> entry = cursor.next();
//            System.out.println("通过scan(H key, ScanOptions options)方法获取匹配键值对:" + entry.getKey() + "---->" + entry.getValue());
//        }
        return SystemResult.ok();
    }
}

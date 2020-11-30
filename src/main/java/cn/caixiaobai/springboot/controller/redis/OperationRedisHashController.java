package cn.caixiaobai.springboot.controller.redis;

import cn.caixiaobai.springboot.dto.User;
import cn.caixiaobai.springboot.result.SystemResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: caixiaobai
 * @Date: 2020/11/30 11:56
 *
 */
@Controller
@RequestMapping("/hash")
@Slf4j
public class OperationRedisHashController {
    //引入redis配置模板lettuce
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(value="/getHash01",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo01(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //redis中某个hash值为redis_cxq，key为user的值，并查看存在和不存在的返回值
        //存在   返回对象
        Object redis_cxq1 = hashOperations.get("redis_cxq","user");
        //不存在  返回null
        Object redis_cxq2 = hashOperations.get("redis_cxq","non-existent");

        return SystemResult.ok(redis_cxq1);
    }

    @RequestMapping(value="/getHash02",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo02(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //获取hash中的值为redis_cxq的map数据，并查看存在和不存在的返回值
        //存在  返回map
        Map redis_cxq1 = hashOperations.entries("redis_cxq");
        //不存在 返回null
        Map redis_cxq2 = hashOperations.entries("non-existent");

        return SystemResult.ok(redis_cxq1);
    }

    @RequestMapping(value="/getHash03",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo03(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //判断hash值为redis_cxq，key为user是否存在
        Boolean aBoolean = hashOperations.hasKey("redis_cxq", "user");

        return SystemResult.ok();
    }

    @RequestMapping(value="/getHash04",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo04(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();

        //返回hash值为redis_cxq的所有key的Set不可重复的无序集合，但是顺序是存入redis中的顺序，取的顺序是一样的
        Set redis_cxq = hashOperations.keys("redis_cxq");
        return SystemResult.ok();
    }

    @RequestMapping(value="/getHash05",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo05(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //返回该hash值为redis_cxq的所有values  返回集合
        List redis_cxq = hashOperations.values("redis_cxq");
        return SystemResult.ok();
    }

    @RequestMapping(value="/getHash06",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo06(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //通过集合条件查询hash值为redis_cxq，key为等于集合条件的值，并返回list集合
        List<String> lu = new ArrayList<>();
        lu.add("user");
        lu.add("user3");
        List redis_cxq = hashOperations.multiGet("redis_cxq", lu);

        return SystemResult.ok();
    }

    @RequestMapping(value="/getHash07",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo07(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //使用场景是用来做计数器，如访问服务器次数
        //当这个值存在就直接设置值，如果不存在就先创建hash为newRedis_cxq,key为user,并赋值为0，并返回我们赋值的数
        //有下面两种方式，integer double
        Long increment = hashOperations.increment("newRedis_cxq", "user", 0);

        Double increment1 = hashOperations.increment("newRedis_cxq2", "user", 0.1);

        return SystemResult.ok();
    }

    @RequestMapping(value="/getHash08",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo08(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();



        return SystemResult.ok();
    }
}

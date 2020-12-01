package cn.caixiaobai.springboot.controller.redis;

import cn.caixiaobai.springboot.dto.User;
import cn.caixiaobai.springboot.result.SystemResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

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
        //返回hash值为newRedis_cxq,key为user所对应value的长度
        Long aLong = hashOperations.lengthOfValue("newRedis_cxq", "user");

        return SystemResult.ok();
    }

    @RequestMapping(value="/getHash09",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo09(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //返回的是redis操作的一些参数，使用没有了解明确……
        RedisOperations operations = hashOperations.getOperations();

        return SystemResult.ok();
    }

    @RequestMapping(value="/getHash10",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo10(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        /**
         * 方法putIfAbsent（H k,HK k,HV v）存入单个map,并判断是否存在，
         * 存在则返回结果为false注意不做任何对redis数据的操作，不存在就添加并返回ture，
         * 这里只比对row列和key列是否存在一样的，如何value列不一样，同样会判断存在返回false，并不会修改这个value
         */

        Boolean aBoolean = hashOperations.putIfAbsent("newRedis_cxq3", "user", 0);

        return SystemResult.ok();
    }

    @RequestMapping(value="/getHash11",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo11(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();

        //单个的方式写入redis,如何已经存在了相同的hash值和key则会覆盖掉value
        hashOperations.put("newRedis_4","user",1);

        return SystemResult.ok();
    }

    @RequestMapping(value="/getHash12",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo12(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //声明的方式写入redis
        /**
         * 如果hash值一样，key也相同会覆盖，如果key不相同会在这个hash值里面继续添加
         */
        Map map=new HashMap<>();
        map.put("k1", "value11");
        map.put("k2", "value22");
        map.put("k3", "value33");
        map.put("k4", "value44");
        hashOperations.putAll("newRedis_cxq5",map);

        return SystemResult.ok();
    }

    @RequestMapping(value="/getHash13",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo13(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        //声明的方式写入redis
        /**
         * 如果hash值一样，key也相同会覆盖，如果key不相同会在这个hash值里面继续添加
         */
        Map map=new HashMap<>();
        map.put("k1", "value11");
        map.put("k2", "value22");
        map.put("k3", "value33");
        map.put("k4", "value44");
        hashOperations.putAll("newRedis_cxq5",map);

        return SystemResult.ok();
    }

    @RequestMapping(value="/getHash14",method = RequestMethod.POST)
    @ResponseBody
    public SystemResult demo14(){
        //获取hash操作对象
        HashOperations hashOperations = redisTemplate.opsForHash();
        /**
         * 匹配获取键值对，ScanOptions.NONE为获取全部键对
         * ScanOptions.scanOptions().match("map1").build() 匹配获取键位map1的键值对,不能模糊匹配。
         * 注意：
         *      这个方法的自动返回不会自动给我们添加上<Map.Entry<Object,Object>>
         */
        Cursor<Map.Entry<Object,Object>> scan = hashOperations.scan("newRedis_cxq5", ScanOptions.scanOptions().match("k1").build());
        //应为实现了迭代器，只能通过迭代来获取keyvalue
        while (scan.hasNext()) {
            Map.Entry<Object, Object> entry = scan.next();
            log.info("通过scan(H key, ScanOptions options)方法获取匹配键值对:" + entry.getKey() + "---->" + entry.getValue());
        }
        
        return SystemResult.ok();
    }
}

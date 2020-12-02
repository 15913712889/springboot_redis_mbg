package cn.caixiaobai.springboot.controller.redis;

import cn.caixiaobai.springboot.SpringbootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: caixiaobai
 * @Date: 2020/12/01 11:43
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)//这个是测试出现问题的解决，需要系写上自己的启动类名称
@Slf4j
public class OperationRedisStringControllerTest {

    //注入RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void demo05(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //TimeUnit.DAYS          //天
        //TimeUnit.HOURS         //小时
        //TimeUnit.MINUTES       //分钟
        //TimeUnit.SECONDS       //秒
        //TimeUnit.MILLISECONDS  //毫秒
        //不存在相同的key就插入，并设置了10秒超时,返回true  存在相同key返回false，不做任何操作
        Boolean aBoolean = valueOperations.setIfAbsent("StringRedis_cxq05", "value0555", 1, TimeUnit.HOURS);
        //Boolean bBoolean = valueOperations.setIfAbsent("StringRedis_cxq05", "value0555555", 1, TimeUnit.HOURS);

    }

    @Test
    public void demo06(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        Map<String,String> m = new HashMap<>();
        m.put("StringRedis_cxq06","value06");
        m.put("StringRedis_cxq06","value060");
        m.put("StringRedis_cxq066","value066");
        //通过批量插入
        //如果插入的key存在就覆盖更新，如果不存在就创建
        valueOperations.multiSet(m);
        System.out.println();
    }

    @Test
    public void demo07(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        Map<String,String> m = new HashMap<>();
        //m.put("StringRedis_cxq07","value07");
        //m.put("StringRedis_cxq077","value0707");

        m.put("StringRedis_cxq0777","value07777");
        m.put("StringRedis_cxq07777","value07777");
        //通过批量插入
        //如果存在不覆盖更新，返回false，不存在就创建
        //当多个数据插入时，有一个存在就返回false ,不会更新这个存在的值,并且这次插入的所有数据都会失败，只有当全都不存在的时候，也就是全都插入成功就会返回true
        Boolean aBoolean = valueOperations.multiSetIfAbsent(m);
        System.out.println(aBoolean);
    }

    @Test
    public void demo08(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("StringRedis_cxq08","value8");
        //通过key获取value的值
        Object stringRedis_cxq08 = valueOperations.get("StringRedis_cxq08");

        System.out.println(stringRedis_cxq08);
    }

    @Test
    public void demo09(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        //valueOperations.set("StringRedis_cxq08","value8");
        //通过key获取value,不存在就返回null并创建key和value。存在就返回存在的value值，然后再覆盖更新value
        Object stringRedis_cxq08 = valueOperations.getAndSet("StringRedis_cxq09","value99");

        System.out.println(stringRedis_cxq08);
    }

    @Test
    public void demo10(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        //valueOperations.set("StringRedis_cxq08","value8");
        //通过key获取value,不存在就返回null并创建key和value。存在就返回存在的value值，然后再覆盖更新value
        Object stringRedis_cxq08 = valueOperations.getAndSet("StringRedis_cxq09","value99");

        System.out.println(stringRedis_cxq08);
    }

    @Test
    public void demo11(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("StringRedis_cxq10","value10");
        valueOperations.set("StringRedis_cxq1010","value1010");
        valueOperations.set("StringRedis_cxq101010","value101010");

        List<String> lk = new ArrayList<>();
        lk.add("StringRedis_cxq10");
        lk.add("StringRedis_cxq1010");
        lk.add("StringRedis_cxq101010");
        //传入key的集合，返回value的集合
        List list = valueOperations.multiGet(lk);

        System.out.println(list);
    }

    @Test
    public void demo12(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("StringRedis_cxq12","15");
        //当value是一个整数时，获取到value的值是加上一个1，如value是10，则获取到的为11.如果value不是整数，会报错
        //最后会将的到的这个stringRedis_cxq12值赋值给value,也就是修改了value的值
        Long stringRedis_cxq12 = valueOperations.increment("StringRedis_cxq12");

        System.out.println();
    }

    @Test
    public void demo13(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        //valueOperations.set("StringRedis_cxq13","15");
        //当key存在时：当value是一个整数时，获取到加上后面这个参数的值得到35，也就是以整数的形势增加，使用场景是计数器
        //当key不存在,就创建一个key,value为后面的参数20
        //最后会将的到的这个stringRedis_cxq12值赋值给value,也就是修改了value的值
        Long stringRedis_cxq12 = valueOperations.increment("StringRedis_cxq1313",20);

        System.out.println();
    }

    @Test
    public void demo14(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("StringRedis_cxq14","15");
        //当key存在时：当value是一个整数时，获取到加上后面这个参数的值得到15.1,也就是已double的形势增加
        //当key不存在,就创建一个key,value为后面的参数0.1
        //最后会将的到的这个stringRedis_cxq12值赋值给value,也就是修改了value的值
        double stringRedis_cxq12 = valueOperations.increment("StringRedis_cxq14",0.1);

        System.out.println();
    }

    @Test
    public void demo15(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        //valueOperations.set("StringRedis_cxq15","15");
        //获取的值为整数时，得到减1的参数，如value为15的到的为14
        //当这个key不存在，会创建，并赋值为0，所以得到-1
        //最后会将的到的这个stringRedis_cxq15值赋值给value,也就是修改了value的值
        Long stringRedis_cxq15 = valueOperations.decrement("StringRedis_cxq1515");

        System.out.println(stringRedis_cxq15);
    }

    @Test
    public void demo16(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        //valueOperations.set("StringRedis_cxq16","16");
        //获取的值为整数时，得到value减你携带都参数的最后的值，如value为16，参数为2，的到的为14
        //当这个key不存在，会创建，并赋值为0，所以得到-2
        //最后会将的到的这个值赋值给value
        Long stringRedis_cxq16 = valueOperations.decrement("StringRedis_cxq16",2);

        System.out.println(stringRedis_cxq16);
    }

    @Test
    public void demo17(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("StringRedis_cxq17","17");
        //在这个key的value值后面添加字符串，返回的为字符串的长度
        Integer append = valueOperations.append("StringRedis_cxq17", "ss");
        String s="17ss";
        int length = s.length();
        System.out.println();
    }

    @Test
    public void demo18(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("StringRedis_cxq18","18181818");
        //获取value值
        Object stringRedis_cxq18 = valueOperations.get("StringRedis_cxq18");
        //获取value的值，下标为1到3之间的值
        String stringRedis_cxq181 = valueOperations.get("StringRedis_cxq18", 1, 3);

        System.out.println(stringRedis_cxq181);
    }

    @Test
    public void demo19(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("StringRedis_cxq19","19");
        //该方法使用不明，还需了解
        valueOperations.set("StringRedis_cxq19","19",4);

        System.out.println();
    }

    @Test
    public void demo20(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("StringRedis_cxq20","value20");
        //返回该key对应value的字符串长度
        Long stringRedis_cxq20 = valueOperations.size("StringRedis_cxq20");

        System.out.println();
    }

    @Test
    public void demo21(){
        ValueOperations valueOperations = redisTemplate.opsForValue();

        valueOperations.set("StringRedis_cxq21","value21");
        //偏移量，使用未了解
        Boolean stringRedis_cxq21 = valueOperations.setBit("StringRedis_cxq21", 6, false);

        System.out.println();
    }
}
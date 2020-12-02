package cn.caixiaobai.springboot.controller.redis;

import cn.caixiaobai.springboot.SpringbootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: caixiaobai
 * @Date: 2020/12/02 09:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)//这个是测试出现问题的解决，需要系写上自己的启动类名称
@Slf4j
public class OperationRedisListControllerTest {

    //注入RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void demo01(){

        ListOperations listOperations = redisTemplate.opsForList();
        //往集合右边添加数据，并返回集合长度
        //Long value01 = listOperations.rightPush("Test:demo01", "value0111");
        //获取指定key指定下标区间的value值,返回集合value
        List range = listOperations.range("Test:demo01", 0, 2);

        System.out.println();
    }

    @Test
    public void demo02(){

        ListOperations listOperations = redisTemplate.opsForList();

//        Long value02 = listOperations.rightPush("test:demo02", "value02");
//        Long value022 = listOperations.rightPush("test:demo02", "value022");
//        Long value0222 = listOperations.rightPush("test:demo02", "value0222");
//        Long value02222 = listOperations.rightPush("test:demo02", "value02222");
        //将指定区间的集合截取出来，去除其他的，只保留截取的区间集合
        listOperations.trim("test:demo02",0,2);

        System.out.println();
    }

    @Test
    public void demo03(){

        ListOperations listOperations = redisTemplate.opsForList();

        Long value02 = listOperations.leftPush("test:demo03", "value03");
        Long value022 = listOperations.leftPush("test:demo03", "value033");
        Long value0222 = listOperations.leftPush("test:demo03", "value033");
        Long value02222 = listOperations.leftPush("test:demo03", "value0333");
        //获取集合的长度
        Long size = listOperations.size("test:demo03");

        System.out.println();
    }

    @Test
    public void demo04(){

        ListOperations listOperations = redisTemplate.opsForList();

        //从左边批量添加参数，返回的是集合长度
        Long aLong = listOperations.leftPushAll("test:demo04", "value04", "value044", "value0444", "value04444");

        System.out.println();
    }

    @Test
    public void demo05(){

        ListOperations listOperations = redisTemplate.opsForList();

        List<String> l = new ArrayList<>();
        l.add("value05");
        l.add("value055");
        l.add("value0555");
        l.add("value05555");
        //从左边批量添加参数，返回的是集合长度
        Long aLong = listOperations.leftPushAll("test:demo05", l);

        System.out.println();
    }

    @Test
    public void demo06(){

        ListOperations listOperations = redisTemplate.opsForList();

        List<String> l = new ArrayList<>();
        l.add("value05");
        l.add("value055");
        l.add("value0555");
        l.add("value05555");
        //从左边批量添加参数，返回的是集合长度
        //Long aLong = listOperations.leftPushAll("test:demo06", l);
        //如果存在这个key,就添加元素，并返回数组长度，如果返回0表示不存在这个key
        Long value06 = listOperations.leftPushIfPresent("test:demo06", "value06");

        System.out.println();
    }

    @Test
    public void demo07(){

        ListOperations listOperations = redisTemplate.opsForList();

        List<String> l = new ArrayList<>();
        l.add("value05");
        l.add("value055");
        l.add("value0555");
        l.add("value05555");
        //从左边批量添加参数，返回的是集合长度
        Long aLong = listOperations.leftPushAll("test:demo07", l);
        //从存储在键中的列表中删除等于值的元素的第一个计数事件。count> 0：删除等于从左到右移动的值的第一个元素；
        // count< 0：删除等于从右到左移动的值的第一个元素；count = 0：删除等于value的所有元素。
        listOperations.remove("test:demo07",0,"value05");

        System.out.println();
    }

    @Test
    public void demo08(){

        ListOperations listOperations = redisTemplate.opsForList();

        List<String> l = new ArrayList<>();
        l.add("value05");
        l.add("value055");
        l.add("value0555");
        l.add("value05555");
        //从左边批量添加参数，返回的是集合长度
        Long aLong = listOperations.leftPushAll("test:demo08", l);
        //获取指定下标位置的值
        Object index = listOperations.index("test:demo08", 0);

        System.out.println();
    }

    @Test
    public void demo09(){

        ListOperations listOperations = redisTemplate.opsForList();

        List<String> l = new ArrayList<>();
        l.add("value05");
        l.add("value055");
        l.add("value0555");
        l.add("value05555");
        //从左边批量添加参数，返回的是集合长度
        Long aLong = listOperations.leftPushAll("test:demo09", l);
        //移除左边的第一个元素
        Object o = listOperations.leftPop("test:demo09");

        System.out.println();
    }


    @Test
    public void demo10(){

        ListOperations listOperations = redisTemplate.opsForList();

//        List<String> l = new ArrayList<>();
//        l.add("value05");
//        l.add("value055");
//        l.add("value0555");
//        l.add("value05555");
//        //从左边批量添加参数，返回的是集合长度
//        Long aLong = listOperations.leftPushAll("test:demo10", l);
        //TimeUnit.DAYS          //天
        //TimeUnit.HOURS         //小时
        //TimeUnit.MINUTES       //分钟
        //TimeUnit.SECONDS       //秒
        //TimeUnit.MILLISECONDS  //毫秒
        //移除左边的第一个元素，在多久的时间里还是会移除左边第一个元素

        //该方法未理解清楚

        Object o = listOperations.leftPop("test:demo10",10, TimeUnit.SECONDS );

        System.out.println();
    }

    @Test
    public void demo11(){

        ListOperations listOperations = redisTemplate.opsForList();

        Long value11 = listOperations.rightPush("test:demo11", "value11");
        Long value12 = listOperations.rightPush("test:demo1111", "value1111");
        //第一个key从右边移除一个元素，将移除的这个元素从左边添加到第二个key中
        Object o = listOperations.rightPopAndLeftPush("test:demo11", "test:demo1111");

        System.out.println();
    }

    @Test
    public void demo12(){

        ListOperations listOperations = redisTemplate.opsForList();

        Long value11 = listOperations.rightPush("test:demo12", "value12");
        Long value12 = listOperations.rightPush("test:demo1212", "value1212");
        //第一个key从右边移除一个元素，将移除的这个元素从左边添加到第二个key中
        //该方法使用不明
        //Object o = listOperations.rightPopAndLeftPush("test:demo12", "test:demo1212",);

        System.out.println();
    }
}
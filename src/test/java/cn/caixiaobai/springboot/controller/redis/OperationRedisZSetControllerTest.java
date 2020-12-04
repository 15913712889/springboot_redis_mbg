package cn.caixiaobai.springboot.controller.redis;

import cn.caixiaobai.springboot.SpringbootApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: caixiaobai
 * @Date: 2020/12/03 09:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)//这个是测试出现问题的解决，需要系写上自己的启动类名称
public class OperationRedisZSetControllerTest {

    //注入RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void demo1(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

        //添加zset成功返回true
        //如果key和value存在相同的，score不同的话，会覆盖score的值，并返回false
        //如果value不同，会添加一条新数据
        Boolean value1 = zSetOperations.add("zset:test:demo1", "value2", 0.2);

        System.out.println();

    }

    @Test
    public void demo01(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();
        //通过TypedTuple方式新增数据。
        ZSetOperations.TypedTuple<Object> typedTuple1 = new DefaultTypedTuple<Object>("E",6.0);
        ZSetOperations.TypedTuple<Object> typedTuple2 = new DefaultTypedTuple<Object>("F",7.0);
        ZSetOperations.TypedTuple<Object> typedTuple3 = new DefaultTypedTuple<Object>("G",5.0);
        Set<ZSetOperations.TypedTuple<Object>> typedTupleSet = new HashSet<ZSetOperations.TypedTuple<Object>>();
        typedTupleSet.add(typedTuple1);
        typedTupleSet.add(typedTuple2);
        typedTupleSet.add(typedTuple3);
        zSetOperations.add("zset:test:demo01",typedTupleSet);
        System.out.println();

    }

    @Test
    public void demo2(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo2", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo2", "B", 3);
        Boolean c = zSetOperations.add("zset:test:demo2","C",2);
        Boolean d = zSetOperations.add("zset:test:demo2", "D", 5);
        //返回移除成功的个数，这个方法后面可以是移除一个，也可以是多个
        Long l = zSetOperations.remove("zset:test:demo2", "A","B");


        System.out.println();

    }

    @Test
    public void demo3(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo3", "A", 1);
        //score加上一个double值，使用场景可以修改排序
        Double a1 = zSetOperations.incrementScore("zset:test:demo3", "A", 1.0);


        System.out.println();

    }

    @Test
    public void demo4(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo4", "A", 1);
        //获取变量中元素的索引,下标开始位置为0。
        Long a1 = zSetOperations.rank("zset:test:demo4", "A");

        System.out.println();

    }

    @Test
    public void demo5(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo5", "A", 1);

        //获取倒序排列的索引值。
        Long a1 = zSetOperations.reverseRank("zset:test:demo5", "A");

        System.out.println();

    }

    @Test
    public void demo6(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo6", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo6", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo6", "C", 3);
        Boolean d = zSetOperations.add("zset:test:demo6", "D", 4);
        Boolean e = zSetOperations.add("zset:test:demo6", "E", 5);
        //获取指定区间的value 集合
        Set range = zSetOperations.range("zset:test:demo6", 0, 2);

        System.out.println();

    }

    @Test
    public void demo7(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo7", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo7", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo7", "C", 3);
        Boolean d = zSetOperations.add("zset:test:demo7", "D", 4);
        Boolean e = zSetOperations.add("zset:test:demo7", "E", 5);
        //获取指定区间的value加上score 集合
        Set<ZSetOperations.TypedTuple<String>> set = zSetOperations.rangeWithScores("zset:test:demo7", 0, 2);
        for (ZSetOperations.TypedTuple<String> s:set) {
            Double score = s.getScore();
            String value = s.getValue();
            System.out.println("value:"+value);
            System.out.println("score:"+score);
        }
        System.out.println();

    }

    @Test
    public void demo8(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo8", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo8", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo8", "C", 3);
        Boolean d = zSetOperations.add("zset:test:demo8", "D", 4);
        Boolean e = zSetOperations.add("zset:test:demo8", "E", 5);
        //也是获取指定区间的value集合,只是参数类型是double
        Set set = zSetOperations.rangeByScore("zset:test:demo8", 0, 2.0);
        //这个方法还不了解
        Set<ZSetOperations.TypedTuple<String>> s= zSetOperations.rangeByScore("zset:test:demo8", 0, 1.0,3,4);
        //这方法和上面方法一样，不过返回值中带score值
        Set<ZSetOperations.TypedTuple<String>> set1 = zSetOperations.rangeByScoreWithScores("zset:test:demo8", 0, 2.0);

        System.out.println();

    }

    @Test
    public void demo9(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo9", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo9", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo9", "C", 3);
        Boolean d = zSetOperations.add("zset:test:demo9", "D", 4);
        Boolean e = zSetOperations.add("zset:test:demo9", "E", 5);
        //索引倒序排列指定区间元素。
        Set set = zSetOperations.reverseRange("zset:test:demo9", 0, 4);

        System.out.println();

    }

    @Test
    public void demo10(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo10", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo10", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo10", "C", 3);
        Boolean d = zSetOperations.add("zset:test:demo10", "D", 4);
        Boolean e = zSetOperations.add("zset:test:demo10", "E", 5);
        //倒序排列指定区间的value,下标从0开始
        Set set = zSetOperations.reverseRange("zset:test:demo9", 0, 4);


        //取对应区间的value和score倒序排列，下标从1开始，可以是double类型的数
        Set<ZSetOperations.TypedTuple<String>> set1 = zSetOperations.reverseRangeByScoreWithScores("zset:test:demo9", 1, 4.9);

        //返回value的集合的指定区间，从1开始，倒序排列指定分值区间元素。
        Set set2 = zSetOperations.reverseRangeByScore("zset:test:demo9", 2, 3);

        //倒序排列指定区间的value值，注意是先倒序排列后，再取倒序排列的集合中的值，第三参数代表从哪个下标开始取数、下标以0开始，第四个参数表示取几个value值，
        Set set4 = zSetOperations.reverseRangeByScore("zset:test:demo9", 1, 5, 1, 2);

        //这个方法和上面的一个意思，返回值不同，返回了value值和score值
        Set<ZSetOperations.TypedTuple<String>> set5 = zSetOperations.reverseRangeByScoreWithScores("zset:test:demo9", 1, 5, 0, 2);

        System.out.println();

    }


    @Test
    public void demo11(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();

//
//        Boolean a = zSetOperations.add("zset:test:demo11", "A", 1);
//        Boolean b = zSetOperations.add("zset:test:demo11", "B", 2);
//        Boolean c = zSetOperations.add("zset:test:demo11", "C", 3);
//        Boolean d = zSetOperations.add("zset:test:demo11", "D", 4);
//        Boolean e = zSetOperations.add("zset:test:demo11", "E", 5);

        Long count = zSetOperations.count("zset:test:demo11", 1, 5);
        //获取区间值的个数。
        System.out.println();

    }


    @Test
    public void demo12(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo12", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo12", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo12", "C", 3);
        Boolean d = zSetOperations.add("zset:test:demo12", "D", 4);
        Boolean e = zSetOperations.add("zset:test:demo12", "E", 10);
        //获取这个key的长度，也就是大小
        Long size = zSetOperations.size("zset:test:demo12");

        System.out.println();

    }

    @Test
    public void demo13(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo13", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo13", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo13", "C", 3);
        Boolean d = zSetOperations.add("zset:test:demo13", "D", 4);
        Boolean e = zSetOperations.add("zset:test:demo13", "E", 5);
        //获取变量中元素的个数。
        Long aLong = zSetOperations.zCard("zset:test:demo13");

        System.out.println();

    }

    @Test
    public void demo14(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo14", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo14", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo14", "C", 3);
        Boolean d = zSetOperations.add("zset:test:demo14", "D", 4);
        Boolean e = zSetOperations.add("zset:test:demo14", "E", 5);
        //获取元素的score列的值
        Double a1 = zSetOperations.score("zset:test:demo14", "A");

        System.out.println();

    }

    @Test
    public void demo15(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo15", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo15", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo15", "C", 3);
        Boolean d = zSetOperations.add("zset:test:demo15", "D", 4);
        Boolean e = zSetOperations.add("zset:test:demo15", "E", 5);
        //根据集合下标移除区间元素。下标是从0开始，返回的是移除的个数
        //Long aLong = zSetOperations.removeRange("zset:test:demo15", 0, 2);
        //通过score数值区间移除元素，也就是通过score列的值得排序来移除区间元素、返回的是移除的个数
        Long aLong = zSetOperations.removeRangeByScore("zset:test:demo15", 1, 3);

        System.out.println();

    }

    @Test
    public void demo16(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo16", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo16", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo16", "C", 3);
        Boolean d = zSetOperations.add("zset:test:demo16", "D", 4);
        Boolean e = zSetOperations.add("zset:test:demo16", "E", 5);
        Boolean f = zSetOperations.add("zset:test:demo1616", "F", 6);
        Boolean g = zSetOperations.add("zset:test:demo1616", "G", 7);
        Boolean h = zSetOperations.add("zset:test:demo1616", "H", 9);
        Boolean i = zSetOperations.add("zset:test:demo161616", "I", 10);
        Boolean j = zSetOperations.add("zset:test:demo16161616", "J", 11);
        //根据集合下标移除区间元素。下标是从0开始
        //将两个集合的数据放到，第三个集合中，如果第三个集合不存在就会创建，如果存在会添加到里面，重复value的会覆盖并修改score的值，返回的是前两个集合中的元素个数之和
        Long aLong = zSetOperations.unionAndStore("zset:test:demo16", "zset:test:demo1616", "zset:test:demo161616");

        List l = new ArrayList<>();
        l.add("zset:test:demo161616");
        l.add("zset:test:demo16161616");
        //和上面这个方法一样，将多个集合放在最后一个集合中，返回的是所有集合元素之和
        Long aLong1 = zSetOperations.unionAndStore("zset:test:demo16", l, "zset:test:demo16Last");
        //这三个方法暂时不了解有什么区别，和上面这个方法一样的效果
        Long aLong2 = zSetOperations.unionAndStore("zset:test:demo16", l, "zset:test:demo16Last2", RedisZSetCommands.Aggregate.SUM);
        Long aLong3 = zSetOperations.unionAndStore("zset:test:demo16", l, "zset:test:demo16Last3", RedisZSetCommands.Aggregate.MIN);
        Long aLong4 = zSetOperations.unionAndStore("zset:test:demo16", l, "zset:test:demo16Last4", RedisZSetCommands.Aggregate.MAX);

        //该方法暂不了解
        Long aLong5 = zSetOperations.unionAndStore("zset:test:demo16", l, "zset:test:demo16Last5", RedisZSetCommands.Aggregate.MAX, RedisZSetCommands.Weights.of(3));

        System.out.println();

    }


    @Test
    public void demo17(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo17", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo1717", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo171717", "C", 3);
        Boolean d = zSetOperations.add("zset:test:demo17171717", "D", 4);
        Boolean e = zSetOperations.add("zset:test:demo1717171717", "E", 5);
        //获取2个变量的交集存放到第3个变量里面。
        Long aLong = zSetOperations.intersectAndStore("zset:test:demo17", "zset:test:demo1717", "zset:test:demo17last");
        // 获取多个变量的交集存放到第3个变量里面。
        List l = new ArrayList();
        l.add("zset:test:demo17171717");
        l.add("zset:test:demo1717171717");
        Long aLong2 = zSetOperations.intersectAndStore("zset:test:demo17", l, "zset:test:demo17last2");
        System.out.println();

    }


    @Test
    public void demo18(){

        ZSetOperations zSetOperations = redisTemplate.opsForZSet();


        Boolean a = zSetOperations.add("zset:test:demo18", "A", 1);
        Boolean b = zSetOperations.add("zset:test:demo18", "B", 2);
        Boolean c = zSetOperations.add("zset:test:demo18", "C", 3);
        //获取该集合所有value值
        Set set = zSetOperations.rangeByLex("zset:test:demo18", RedisZSetCommands.Range.range());
        //该方法还不理解
        zSetOperations.rangeByLex("zset:test:demo18", RedisZSetCommands.Range.range(), RedisZSetCommands.Limit.limit());

        //获取到value和score
        Cursor<ZSetOperations.TypedTuple<Object>> value1 = zSetOperations.scan("zset:test:demo18", ScanOptions.scanOptions().match("A").build());
        while (value1.hasNext()){
            ZSetOperations.TypedTuple<Object> typedTuple = value1.next();
            System.out.println("通过scan(K key, ScanOptions options)方法获取匹配的值:" + typedTuple.getValue()+"\t"+typedTuple.getScore());
        }
        System.out.println();

    }
}
package cn.caixiaobai.springboot.controller.redis;

import cn.caixiaobai.springboot.SpringbootApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: caixiaobai
 * @Date: 2020/12/02 11:25
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)//这个是测试出现问题的解决，需要系写上自己的启动类名称
public class OperationRedisSetControllerTest {

    //注入RedisTemplate
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void demo01(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序的集合,相同的会覆盖，不同的会添加
        Long add = setOperations.add("test:set:demo01", "value1", "value2", "valu3", "value4", "value5");
        System.out.println();
    }

    @Test
    public void demo02(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo02", "value1", "value2", "valu3", "value4", "value5");
        Long add2 = setOperations.add("test:set:demo02", "value6");

        //移除指定的value，移除成功的个数
        Long remove = setOperations.remove("test:set:demo02", "value1", "value2");
        System.out.println();
    }


    @Test
    public void demo03(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
//        Long add = setOperations.add("test:set:demo03", "value1", "value2", "value3", "value4", "value5");
//        Long add2 = setOperations.add("test:set:demo03", "value6");
        //随机去除一个元素，并返回去除的值
        Object pop = setOperations.pop("test:set:demo03");

        System.out.println();
    }

    @Test
    public void demo04(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo04", "value1", "value2", "value3", "value4", "value5");
        Long add2 = setOperations.add("test:set:demo04", "value6");
        //随机去除指定个数元素，并返回去除的值的list集合
        List pop = setOperations.pop("test:set:demo04", 2);

        System.out.println();
    }

    @Test
    public void demo05(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo05", "value1", "value2", "value3", "value4", "value5");
        Long add2 = setOperations.add("test:set:demo05", "value6");

        Long add3 = setOperations.add("test:set:demo055", "value7", "value8", "value9", "value10", "value11");
        //将test:set:demo05中的value值为value1的移除，并添加到test:set:demo055
        Boolean value1 = setOperations.move("test:set:demo05", "value1", "test:set:demo055");
        System.out.println();
    }

    @Test
    public void demo06(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo06", "value1", "value2", "value3", "value4", "value5");
        //获取某个指定set类型的key的长度
        Long size = setOperations.size("test:set:demo06");

        System.out.println();
    }

    @Test
    public void demo07(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo07", "value1", "value2", "value3", "value4", "value5");
        //判断这个set的类型的集合中是否存在这个值
        Boolean value1 = setOperations.isMember("test:set:demo07", "value6");//false
        Boolean value2 = setOperations.isMember("test:set:demo07", "value1");//true

        System.out.println();
    }

    @Test
    public void demo08(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo08", "value1", "value2", "value3", "value4", "value5");

        Long add3 = setOperations.add("test:set:demo088", "value7", "value8", "value9", "value10", "value11");
        //获取两个集合中的交集，返回的是一个交集的集合
        Set intersect = setOperations.intersect("test:set:demo08", "test:set:demo088");

        System.out.println();
    }

    @Test
    public void demo09(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo09", "value1", "value2", "value3", "value4", "value5");

        Long add2 = setOperations.add("test:set:demo099", "value7", "value8", "value9", "value10", "value11","value1");

        Long add3 = setOperations.add("test:set:demo0999", "value12", "value13", "value14", "value15", "value16","value1");

        //自己指定集合做交集
        Set s = new HashSet();
        s.add("test:set:demo099");
        s.add("test:set:demo0999");
        //获取多个集合中的交集，注意：这个集合写的是redis库中的set类型的key，返回的是一个交集的集合
        Set intersect = setOperations.intersect("test:set:demo09", s);

        System.out.println();
    }

    @Test
    public void demo010(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo10", "value1", "value2", "value3", "value4", "value5");

        Long add2 = setOperations.add("test:set:demo1010", "value7", "value8", "value9", "value10", "value11","value1");

        Long add3 = setOperations.add("test:set:demo101010", "value12", "value13", "value14", "value15", "value16","value1");

        //自己指定集合做交集
        Set s = new HashSet();
        s.add("test:set:demo10");
        s.add("test:set:demo1010");
        s.add("test:set:demo101010");
        //获取多个集合中的交集，注意：这个集合写的是redis库中的set类型的key，返回的是一个交集的集合
        Set intersect = setOperations.intersect( s);

        System.out.println();
    }

    @Test
    public void demo011(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo11", "value1", "value2", "value3", "value4", "value5");

        Long add2 = setOperations.add("test:set:demo1111", "value7", "value8", "value9", "value10", "value11","value1");

        Long add3 = setOperations.add("test:set:demo111111", "value12", "value13", "value14", "value15", "value16","value1");

        
        //获取多个集合中的交集，返回的是交集的个数
        Long aLong = setOperations.intersectAndStore("test:set:demo11", "test:set:demo1111", "test:set:demo111111");

        System.out.println();
    }

    @Test
    public void demo012(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo12", "value1", "value2", "value3", "value4", "value5");

        Long add2 = setOperations.add("test:set:demo1212", "value7", "value8", "value9", "value10", "value11","value1");

        Long add3 = setOperations.add("test:set:demo121212", "value12", "value13", "value14", "value15", "value16","value1");

        Long add4 = setOperations.add("test:set:demo12121212", "value17", "value18", "value19", "value20", "value21","value1");

        List l = new ArrayList();
        l.add("test:set:demo1212");
        l.add("test:set:demo121212");
        //获取多个集合中的交集，返回的是交集的个数
        Long aLong = setOperations.intersectAndStore("test:set:demo11", l,"test:set:demo12121212");

        System.out.println();
    }

    /**
     * 和上面两个方法一样，只是携带参数不一样，效果也是一样的都是获取交集的个数
     */
    @Test
    public void demo013(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo13", "value1", "value2", "value3", "value4", "value5");

        Long add2 = setOperations.add("test:set:demo1313", "value7", "value8", "value9", "value10", "value11","value1");

        Long add3 = setOperations.add("test:set:demo131313", "value12", "value13", "value14", "value15", "value16","value1");



        List l = new ArrayList();
        l.add("test:set:demo13");
        l.add("test:set:demo1313");
        //获取多个集合中的交集，返回的是交集的个数
        Long aLong = setOperations.intersectAndStore( l,"test:set:demo131313");

        System.out.println();
    }

    /**
     * 这三个方法 都是一样效果，参数不同
     *     @Nullable
     *     Set<V> union(K var1, K var2);
     *
     *     @Nullable
     *     Set<V> union(K var1, Collection<K> var2);
     *
     *     @Nullable
     *     Set<V> union(Collection<K> var1);
     */
    @Test
    public void demo014(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo14", "value1", "value2", "value3", "value4", "value5");

        Long add2 = setOperations.add("test:set:demo1414", "value7", "value8", "value9", "value10", "value11","value1");

        //返回的是并集的值
        Set union = setOperations.union("test:set:demo14", "test:set:demo1414");

        System.out.println();
    }

    /**
     * 这个三个方法一样的效果
     *     @Nullable
     *     Long unionAndStore(K var1, K var2, K var3);
     *
     *     获取多个集合变量的并集，赋值给var3集合，返回的是var3集合的个数
     *     @Nullable
     *     Long unionAndStore(K var1, Collection<K> var2, K var3);
     *
     *     获取多个集合变量的并集，赋值给var3集合，返回的是var3集合的个数,和上面方法相同，只是书写方式不一样
     *     @Nullable
     *     Long unionAndStore(Collection<K> var1, K var2);
     */
    @Test
    public void demo015(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo15", "value1", "value2", "value3", "value4", "value5");

        Long add2 = setOperations.add("test:set:demo1515", "value7", "value8", "value9", "value10", "value11","value1");

        Long add3 = setOperations.add("test:set:demo151515", "value12", "value13", "value14", "value15", "value16","value1");

        //获取前两个的并集，并将第三个集合的内容替换成这个并集，也就是将前两个的并集赋值给了第三个集合，返回的是最后一个集合的个数
        Long aLong = setOperations.unionAndStore("test:set:demo15", "test:set:demo1515", "test:set:demo151515");

        System.out.println();
    }

    @Test
    public void demo016(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo16", "value1", "value2", "value3", "value4", "value5");

        Long add2 = setOperations.add("test:set:demo1616", "value7", "value8", "value9", "value10", "value11","value1");


        //获取的是前面一个集合减去后面一个集合相同的value，得到的是被减数剩下的集合
        Set difference = setOperations.difference("test:set:demo16", "test:set:demo1616");//剩下 "value2", "value3", "value4", "value5"

        Set difference2 = setOperations.difference("test:set:demo1616", "test:set:demo16");//剩下 "value7", "value8", "value9", "value10", "value11"

        System.out.println();
    }

    @Test
    public void demo017(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo17", "value1", "value2", "value3", "value4", "value5","value7");

        Long add2 = setOperations.add("test:set:demo1717", "value7", "value8", "value9", "value10", "value11","value1");

        Long add3 = setOperations.add("test:set:demo171717", "value12", "value13", "value14", "value15", "value16","value1");

        List l = new ArrayList();
        l.add("test:set:demo1717");
        l.add("test:set:demo171717");

        //获取的是前面一个集合减去后面多个集合中相同的value，得到的是被减数剩下的集合
        Set difference = setOperations.difference("test:set:demo17", l);//剩下 "value2", "value3", "value4", "value5"

        System.out.println();
    }


    @Test
    public void demo018(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo18", "value1", "value2", "value3", "value4", "value5","value7");

        Long add2 = setOperations.add("test:set:demo1818", "value7", "value8", "value9", "value10", "value11","value1");

        Long add3 = setOperations.add("test:set:demo181818", "value12", "value13", "value14", "value15", "value16","value2");

        List l = new ArrayList();
        l.add("test:set:demo18");
        l.add("test:set:demo1818");
        l.add("test:set:demo181818");

        //用第一个添加到l集合中的set集合做为被减数，其他的做为减数，依次做减法
        Set difference = setOperations.difference(l);//剩下"value3", "value4", "value5"

        System.out.println();
    }

    @Test
    public void demo019(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo19", "value1", "value2", "value3", "value4", "value5","value7");

        Long add2 = setOperations.add("test:set:demo1919", "value7", "value8", "value9", "value10", "value11","value1");

        //将第一个参数做为被减数，第二参数为减数，被减数减去减数中相同的value,将剩下的保存到第三个参数中，如果第三个参数不存在会创建
        Long aLong = setOperations.differenceAndStore("test:set:demo19", "test:set:demo1919", "test:set:demo19Last");//剩下"value3", "value4", "value5"

        System.out.println();
    }

    @Test
    public void demo020(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo20", "value1", "value2", "value3", "value4", "value5","value7");

        Long add2 = setOperations.add("test:set:demo2020", "value7", "value8", "value9", "value10", "value11","value1");

        Long add3 = setOperations.add("test:set:demo202020", "value12", "value13", "value14", "value15", "value16","value3");

        List l = new ArrayList();
        l.add("test:set:demo2020");
        l.add("test:set:demo202020");
        //l中的集合依次做为减数，减去相同的value，得到最后的结果，保存到最后一个参数上，最后一个参数不存在就创建
        Long aLong = setOperations.differenceAndStore("test:set:demo20", l, "test:set:demo20Last");//剩下"value3", "value4", "value5"

        System.out.println();
    }

    @Test
    public void demo021(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo21", "value1", "value2", "value3", "value4", "value5","value7");

        Long add2 = setOperations.add("test:set:demo2121", "value7", "value8", "value9", "value10", "value11","value1");

        Long add3 = setOperations.add("test:set:demo212121", "value12", "value13", "value14", "value15", "value16","value3");

        List l = new ArrayList();
        l.add("test:set:demo21");
        l.add("test:set:demo2121");
        l.add("test:set:demo212121");
        //将第一个添加到l集合中的set集合做为被减数，其他的做为减数，的到结果后，保存到最后一个参数上
        Long aLong = setOperations.differenceAndStore(l,"test:set:demo21Last");//剩下"value3", "value4", "value5"

        System.out.println();
    }


    @Test
    public void demo022(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo22", "value1", "value2", "value3", "value4", "value5","value7");
        //获取该key的所有value集合
        Set members = setOperations.members("test:set:demo22");
        System.out.println();
    }

    @Test
    public void demo023(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo23", "value1", "value2", "value3", "value4", "value5","value7");
        //获取该key的一个随机value
        Object o = setOperations.randomMember("test:set:demo23");
        //指定随机获取几个value的值，返回获取的集合
        List list = setOperations.randomMembers("test:set:demo23", 2);

        System.out.println();
    }

    @Test
    public void demo024(){
        SetOperations setOperations = redisTemplate.opsForSet();
        //添加一个set集合，set是一个无序不重复的集合,返回的是成功的个数
        Long add = setOperations.add("test:set:demo24", "value1", "value2", "value3", "value4", "value5","value7");
        // 匹配获取键值对，ScanOptions.NONE为获取全部键值对；ScanOptions.scanOptions().match("C").build()匹配获取键位map1的键值对,不能模糊匹配。
        Cursor<Object> value1 = setOperations.scan("test:set:demo24", ScanOptions.scanOptions().match("value1").build());
        while (value1.hasNext()){
            Object object = value1.next();
            System.out.println("通过scan(K key, ScanOptions options)方法获取匹配的值:" + object);
        }
        System.out.println();
    }
}
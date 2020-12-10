package cn.caixiaobai.springboot.service.impl;

import cn.caixiaobai.springboot.mapper.UserMapper;
import cn.caixiaobai.springboot.pojo.User;
import cn.caixiaobai.springboot.service.QueryRedisControllerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * @Author: caixiaobai
 * @Date: 2020/12/08 14:29
 */
@Service
public class QueryRedisControllerServiceImpl implements QueryRedisControllerService {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "QueryRedisControllerServiceImpl",key = "#root.method.name+#userName")
    /**
     * 生成的key为：QueryRedisControllerServiceImpl::Demo03蔡序强
     * 属性condition：
     *      有的时候我们可能并不希望缓存一个方法所有的返回结果。通过condition属性可以实现这一功能。condition属性默认为空，
     *      表示将缓存所有的调用情形。其值是通过SpringEL表达式来指定的，当为true时表示进行缓存处理；当为false时表示不进行缓存处理，
     *      即每次调用该方法时该方法都会执行一次。如下示例表示只有当user的id为偶数时才会进行缓存。
     *      例子： @Cacheable(value={"users"}, key="#user.id", condition="#user.id%2==0")
     */
    public User Demo03(String userName) {

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("userName",userName));

        return user;
    }

    @Override
    @Cacheable(value = "QueryRedisControllerServiceImpl",key = "#root.method.name",sync = true)
    /**
     * sync:
     *      在多线程环境下，某些操作可能使用相同参数同步调用。默认情况下，缓存不锁定任何资源，可能导致多次计算，而违反了缓存的目的。
     *      对于这些特定的情况，属性 sync 可以指示底层将缓存锁住，使只有一个线程可以进入计算，而其他线程堵塞，直到返回结果更新到缓存中
     */
    public Integer Demo04() {

        System.out.println("执行了方法!");
        Integer integer = new Random().nextInt(100);
        return integer;
    }

    @Override
    @CachePut(value = "QueryRedisControllerServiceImpl",key = "#root.method.name")
    /**
     *   @CachePut
     *      每次都会执行方法，并将结果存入指定的缓存中
     */
    public Integer Demo05() {
        System.out.println("执行了方法!");
        Integer integer = new Random().nextInt(100);
        return integer;
    }
}

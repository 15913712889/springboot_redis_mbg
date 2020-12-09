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

/**
 * @Author: caixiaobai
 * @Date: 2020/12/08 14:29
 */
@Service
public class QueryRedisControllerServiceImpl implements QueryRedisControllerService {

    @Autowired
    private UserMapper userMapper;

    @Override

    @Cacheable(cacheNames = "users")
    public User Demo03(String userName) {

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("userName",userName));

        return user;
    }
}

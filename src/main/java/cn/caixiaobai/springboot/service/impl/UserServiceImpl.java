package cn.caixiaobai.springboot.service.impl;

import cn.caixiaobai.springboot.pojo.User;
import cn.caixiaobai.springboot.mapper.UserMapper;
import cn.caixiaobai.springboot.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author caixiaobai
 * @since 2020-11-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}

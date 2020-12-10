package cn.caixiaobai.springboot.mapper;

import cn.caixiaobai.springboot.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author caixiaobai
 * @since 2020-11-24
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}

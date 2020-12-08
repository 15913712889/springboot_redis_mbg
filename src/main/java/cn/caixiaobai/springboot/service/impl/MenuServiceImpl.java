package cn.caixiaobai.springboot.service.impl;

import cn.caixiaobai.springboot.pojo.Menu;
import cn.caixiaobai.springboot.mapper.MenuMapper;
import cn.caixiaobai.springboot.service.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author caixiaobai
 * @since 2020-11-24
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}

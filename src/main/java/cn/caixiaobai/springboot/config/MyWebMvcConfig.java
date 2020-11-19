package cn.caixiaobai.springboot.config;

import cn.caixiaobai.springboot.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * @Author: caixiaobai
 * @Date: 2020/10/19 14:55
 * @Version 1.0
 */
@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer  {

    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 添加自定义拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        InterceptorRegistration interceptorRegistration = registry.addInterceptor(loginInterceptor);

        interceptorRegistration.addPathPatterns("/**")//拦截的访问路径，拦截所有
                .excludePathPatterns("/layui-v2.5.6/**");//排除的请求路径，排除静态资源路径

    }
}

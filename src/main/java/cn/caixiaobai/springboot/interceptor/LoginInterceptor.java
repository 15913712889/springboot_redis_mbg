package cn.caixiaobai.springboot.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: caixiaobai
 * @Date: 2020/10/19 14:33
 * @Version 1.0
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    private static final Set<String> NOT_INTERCEPT_URI = new HashSet<>();//不拦截的URI
//    static {
//        NOT_INTERCEPT_URI.add("/login");
//    }

    /**
     *
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//          String uri = request.getRequestURI();
//          log.info("请求的uri为:" + uri);
//        if (NOT_INTERCEPT_URI.contains(uri)) {
//            log.info("不拦截的请求url为:" + uri);
//            return true;
//        }
//        log.info("拦截de请求为:" + uri);
//        User userInfo = (User) request.getSession().getAttribute("user");
//        if (userInfo == null) {
//            //throw new RuntimeException("用户未登陆");
//           // response.sendRedirect("/login");
//            return true;
//        }
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行
     * （主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

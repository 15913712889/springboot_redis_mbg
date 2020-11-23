package cn.caixiaobai.springboot.interceptor;

import cn.caixiaobai.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    static {
        NOT_INTERCEPT_URI.add("/login/login");
        NOT_INTERCEPT_URI.add("/login");
        NOT_INTERCEPT_URI.add("/myLogin");
        NOT_INTERCEPT_URI.add("/css/login/styles.css");
        NOT_INTERCEPT_URI.add("/css/login/demo.css");
        NOT_INTERCEPT_URI.add("/favicon.ico");
        NOT_INTERCEPT_URI.add("/img/8.jpg");
        NOT_INTERCEPT_URI.add("/css/login/default.css");
        NOT_INTERCEPT_URI.add("/img/user_icon_copy.png");
        NOT_INTERCEPT_URI.add("/layui-v2.5.6/layui/css/layui.css");
        NOT_INTERCEPT_URI.add("/css/login/loaders.css");
        NOT_INTERCEPT_URI.add("/js/login/stopExecutionOnTimeout.js");
        NOT_INTERCEPT_URI.add("/js/login/Particleground.js");
        NOT_INTERCEPT_URI.add("/layui-v2.5.6/layui/layui.js");
        NOT_INTERCEPT_URI.add("/js/login/Treatment.js");
        NOT_INTERCEPT_URI.add("/js/login/login.js");
        NOT_INTERCEPT_URI.add("/img/lock_icon_copy.png");
        NOT_INTERCEPT_URI.add("/js/login/jquery.min.js");
        NOT_INTERCEPT_URI.add("/js/login/jquery-ui.min.js");
        NOT_INTERCEPT_URI.add("/layui-v2.5.6/layui/lay/modules/layer.js");
        NOT_INTERCEPT_URI.add("/layui-v2.5.6/layui/css/modules/layer/default/layer.css");
        NOT_INTERCEPT_URI.add("/layui-v2.5.6/layui/lay/modules/form.js");
        NOT_INTERCEPT_URI.add("/layui-v2.5.6/layui/css/modules/layer/default/loading-0.gif");
    }

    /**
     *
     * 在请求处理之前进行调用（Controller方法调用之前）
     *
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        if(!NOT_INTERCEPT_URI.contains(uri)){
            log.info("被拦截的路径："+uri);
            HttpSession session = request.getSession();
            String  token = (String) session.getAttribute("token");
            String token2 = request.getParameter("token");
            Cookie[] cookies = request.getCookies();
            String token3=null;
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    token3=cookie.getValue();
                }
            }
            boolean verify = TokenUtils.verify(token3);
            if (verify){
                return true;
            }else{
                response.sendRedirect("/login");
                return false;
            }
        }else{
            log.info("没拦截拦截的路径："+uri);
        }
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

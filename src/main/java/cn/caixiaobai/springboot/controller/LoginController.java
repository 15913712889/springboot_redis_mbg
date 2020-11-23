package cn.caixiaobai.springboot.controller;

import cn.caixiaobai.springboot.result.SystemResult;
import cn.caixiaobai.springboot.utils.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: caixiaobai
 * @Date: 2020/10/19 14:51
 * @Version 1.0
 */
@Controller
@Slf4j
public class LoginController {

    @RequestMapping("/myLogin")
    public String Login(String userName, String password, HttpServletRequest request, HttpServletResponse response){
        log.info("来到controller,开始验证用户名和密码");

        if(userName.equals("蔡序强")){
            log.info(userName);
        }

        if(password.equals("105512")){
            log.info(password);
        }
        if(userName.equals("蔡序强")&&password.equals("105512")){
            String token = TokenUtils.token(userName, password);
            Cookie cookie = new Cookie("token",token);
            response.addCookie(cookie);

            //return "/layui/Demo01";
        }

//        request.getRequestDispatcher("/layui/Demo01").forward(request, response);
       return null;
    }

    /**
     ** 跳转到登录页面
     * @return
     */
    @RequestMapping({"/login","/"})
    public String toLogin(){
        return "/login/login";
    }
    @RequestMapping("/toSPage")
    public String toSPage(){
        return "layui/Demo01";
    }
}

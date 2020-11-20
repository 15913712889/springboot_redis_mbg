package cn.caixiaobai.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: caixiaobai
 * @Date: 2020/10/19 14:51
 * @Version 1.0
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String Login(){
        return "/login/login";
    }
}

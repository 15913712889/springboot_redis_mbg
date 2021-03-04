package cn.caixiaobai.springboot;


import cn.hutool.core.date.DateUtil;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Date;

/**
 * @Author: caixiaobai
 * @Date: 2020/10/15 16:11
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("cn.caixiaobai.springboot.mapper")
@Slf4j
@EnableCaching
public class SpringbootApplication {

    @Value("${server.port}")
    private static String ServerPort;
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SpringbootApplication.class, args);
        Environment bean = run.getBean(Environment.class);
        System.out.println(
                "===================================┴┬┴┬／￣＼＿／￣＼ \n"+
                "===================================┬┴┬┴▏　　▏▔▔▔▔▔▔▔＼\n"+
                "===================================┴┬┴／＼　／　　　　　　＼\n"+
                "===================================┬┴∕　　　　　　　／　　　）\n"+
                "===================================┴┬▏加班加班！     　●／▏＼\n"+
                "===================================┬┴▏　　　　　　　　　　　▔█ \n"+
                "===================================┴◢██◣　　　　　　／＼＿＿／\n"+
                "===================================┬█████◣　　　　　　　／　　　\n"+
                "===================================┴█████████████◣　\n"+
                "===================================◢██████████████▆▄\n"+
                "===================================█◤◢██◣◥█████████◤＼　　\n"+
                "===================================◥◢████　████████◤　　 ＼\n"+
                "===================================┴█████　██████◤　　　　　 ﹨\n"+
                "===================================┬│　　　│█████◤　　　　　　　　▏\n"+
                "===================================┴│　　　│　　　　　　　　　　　　　　▏\n"+
                "===================================┬∕　　　∕　　　　／▔▔▔＼　　　　 ∕\n"+
                "===================================*∕＿＿_／﹨　　　∕　　　　　 ＼　　／＼\n"+
                "===================================┴┬┴┬┴┬┴ ＼＿＿＿＼　　　　 ﹨／▔＼﹨／▔＼\n"+
                "===================================▲△▲▲╓╥╥╥╥╥╥╥╥＼　　 ∕　 ／▔﹨　／▔\n"+
                "===================================　＊＊＊╠╬╬╬╬╬╬╬╬＊﹨\n"+
                "=================================== :: Spring Boot ::        (v2.3.4.RELEASE) \n" +
                "================================================================================= \n"+
                "================================================================================= \n"+
                "================================================================================= \n"+
                "===================================项目启动成功! \n"+
                "===================================启动端口号："+bean.getProperty("server.port")+"\n"+
                "===================================启动时间："+DateUtil.date(System.currentTimeMillis())+"\n"+
                "===================================项目访问链接：http://localhost:" +bean.getProperty("server.port")+bean.getProperty("server.servlet.context-path"));
    }
}

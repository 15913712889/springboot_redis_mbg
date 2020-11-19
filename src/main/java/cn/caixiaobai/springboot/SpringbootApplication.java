package cn.caixiaobai.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: caixiaobai
 * @Date: 2020/10/15 16:11
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan("cn.caixiaobai.springboot.mapper")
public class SpringbootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class,args);
    }
}

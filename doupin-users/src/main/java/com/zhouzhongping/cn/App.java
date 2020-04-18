package com.zhouzhongping.cn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan({"com.zhouzhongping.cn.mapper"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }   
}

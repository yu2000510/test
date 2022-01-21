package com.bjpowernode;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.bjpowernode.dao")
public class SpringbootThymeleafShiroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootThymeleafShiroApplication.class, args);
    }

}

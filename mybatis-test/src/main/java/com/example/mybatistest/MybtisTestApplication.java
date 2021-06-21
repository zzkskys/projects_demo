package com.example.mybatistest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackageClasses = MybtisTestApplication.class)
@SpringBootApplication
public class MybtisTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(MybtisTestApplication.class, args);
    }
}

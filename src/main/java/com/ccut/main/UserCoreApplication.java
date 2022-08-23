package com.ccut.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ccut.main.mapper")
public class UserCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserCoreApplication.class, args);
    }

}

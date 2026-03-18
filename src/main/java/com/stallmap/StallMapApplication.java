package com.stallmap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stallmap.**.mapper")
public class StallMapApplication {
    public static void main(String[] args) {
        SpringApplication.run(StallMapApplication.class, args);
    }
}


package com.stallmap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {
        "com.stallmap.module.admin.mapper",
        "com.stallmap.module.user.mapper",
        "com.stallmap.module.vendor.mapper",
        "com.stallmap.module.stall.mapper",
        "com.stallmap.module.review.mapper"
})
public class StallMapApplication {
    public static void main(String[] args) {
        SpringApplication.run(StallMapApplication.class, args);
    }
}


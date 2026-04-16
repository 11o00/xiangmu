package com.stallmap.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({
    "com.stallmap.module.admin.mapper",
    "com.stallmap.module.user.mapper",
    "com.stallmap.module.vendor.mapper",
    "com.stallmap.module.stall.mapper",
    "com.stallmap.module.review.mapper"
})
public class MyBatisPlusConfig {
}
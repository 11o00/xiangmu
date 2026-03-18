package com.stallmap.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 暂时注释掉分页插件配置，先让项目启动
        // PaginationInnerInterceptor paginationInterceptor = new PaginationInnerInterceptor();
        // paginationInterceptor.setDbType(DbType.MYSQL);
        // interceptor.addInnerInterceptor(paginationInterceptor);
        return interceptor;
    }
}


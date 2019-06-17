package com.ddc.server;/*
 * Copyright (C) 2018 royal Inc., All Rights Reserved.
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author dingpengfei
 * @Description: 指定项目为springboot，由此类当作程序入口，自动装配 web 依赖的环境
 * @since 2019-05-09
 */
//@EnableAutoConfiguration
//@EnableWebMvc
@SpringBootApplication(scanBasePackages = {"com.ddc.server"})
@MapperScan("com.ddc.server.mapper")

public class DDCServerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(DDCServerApplication.class, args);
    }

    /**
     * 2、复写该方法，使Spring Boot支持JSP（本质是支持 servlet ）
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DDCServerApplication.class);
    }

}

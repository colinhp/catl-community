package com.catl.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class AlphaConfig {
    //我们会把所有的配置类放到同一个包下
    //我们表示这个配置类也可使用这个注解@SpringBootApplication 但该注解一般标注的是程序入口的注解
    //我们普通的配置类注解用@Configuration  代表是个配置类不是不同类
    @Bean
    public SimpleDateFormat simpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}

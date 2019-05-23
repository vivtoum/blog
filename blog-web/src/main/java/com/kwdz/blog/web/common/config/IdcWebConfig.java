package com.kwdz.blog.web.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共配置 BLOG-WEB
 *
 * @author YT.Hu
 */
@Slf4j
@ComponentScan({"com.kwdz.*"})
@EnableFeignClients({"com.kwdz.*"})
//@EnableRedisHttpSession
@EnableEurekaClient
@Configuration
@RestController
public class IdcWebConfig {

    @Autowired
    private Environment env;

    @GetMapping("/")
    public String index() {
        return "BLOG-WEB：启动成功！";
    }

    /**
     * 启动成功
     */
    @Bean
    public ApplicationRunner applicationRunner() {
        return applicationArguments -> log.info("\n启动成功：" + env.getProperty("blog-web.url"));
    }

}

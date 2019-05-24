package com.kwdz.blog.svc;

import com.kwdz.blog.svc.common.config.BlogSvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * BLOG-SVC 启动类
 *
 * @author YT.Hu
 * @see BlogSvcConfig 公用配置
 */
@SpringBootApplication
public class BlogSvcApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BlogSvcApplication.class);
        application.run();
    }

}

package com.kwdz.blog.web;

import com.kwdz.blog.api.common.properties.PropertiesListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author YT.Hu
 */
@SpringBootApplication
public class BlogWebApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BlogWebApplication.class);
        application.addListeners(new PropertiesListener("config/url.properties"));
        application.run(args);
    }

}

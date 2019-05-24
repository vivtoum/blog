package com.kwdz.blog.svc.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author YT.Hu
 */
@Slf4j
@Component
@Order(value = 2)
public class FtpInitRunner implements CommandLineRunner {

    @Override
    public void run(String... var1) {
        log.info(JobUtil.getUser(new File("D:\\yt.hu\\IdeaProjects\\blog\\blog-svc\\src\\main\\resources\\Interface.txt")).toString());
    }
}
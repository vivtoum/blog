package com.kwdz.blog.svc.job;

import com.kwdz.blog.svc.remoteuser.service.RemoteUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

/**
 * 启动后只执行一次的JOB
 *
 * @author YT.Hu
 */
@Slf4j
@Component
@Order(value = 2)
public class FtpInitRunner implements CommandLineRunner {

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private JobUtil jobUtil;

    @Override
    public void run(String... var1) throws FileNotFoundException {
        jobUtil.refreshUser();
    }
}
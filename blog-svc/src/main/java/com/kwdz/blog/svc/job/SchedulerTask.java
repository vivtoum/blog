package com.kwdz.blog.svc.job;

import com.kwdz.blog.svc.remoteUser.entity.RemoteUserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 定时任务
 *
 * @author YT.Hu
 * @date 2019-5-24 16:20:42
 */
@Slf4j
@Component
public class SchedulerTask {

    private int count = 0;

    //    @Scheduled(cron = "0 0 3 * * ?")
//    @Scheduled(cron = "* * * * * ?")
    private void process() {
        System.out.println("this is scheduler task runing  " + (count++));
    }

}
package com.kwdz.blog.svc.job;

import com.kwdz.blog.api.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author YT.Hu
 * @date 2019-5-24 16:20:42
 */
@Slf4j
@Component
public class SchedulerTask {

    @Autowired
    private JobUtil jobUtil;

    private int count = 0;

    @Scheduled(cron = "0 0 3 * * ?")
//    @Scheduled(cron = "0 */1 * * * ?")
    private void process() {
        jobUtil.refreshUser();
        log.warn("refreshUser times: " + (count++) + " in " + DateUtil.getDateTimeStr());
    }

}
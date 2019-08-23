package com.cjbs.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author shj
 */
@Component
public class ScheduleTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduleTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //一小时
    private static final long ONE_HOURS = 60 * 60 * 1000;
    //一天
    private static final long ONE_DAY = 60 * 60 * 1000 *24;
    //5秒
    private static final long ONE_SECONDS = 1000;

    @Scheduled(fixedRate = ONE_SECONDS)
    public void scheduledTask() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
    @Scheduled(fixedRate = ONE_DAY)
    public void scheduledTask1() {
        log.info("我是一个每间隔一天执行一次的调度任务");
    }
    @Scheduled(fixedDelay  = ONE_HOURS)
    public void scheduledTask2() {
        log.info("我是一个每间隔一小时执行一次的调度任务");
    }

}

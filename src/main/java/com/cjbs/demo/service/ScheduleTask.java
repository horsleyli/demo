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

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //一小时
    private static final long ONE_HOURS = 60 * 60 * 1000;
    //一天
    private static final long ONE_DAY = 60 * 60 * 1000 *24;
    //10秒
    private static final long TEN_SECONDS = 10000;
    //5秒
    private static final long FIVE_SECONDS = 5000;


    /**
     * 1、fixedDelay控制方法执行的间隔时间，是以上一次方法执行完开始算起，如上一次方法执行阻塞住了，那么直到上一次执行完，并间隔给定的时间后，执行下一次。
     *
     * 2、fixedRate是按照一定的速率执行，是从上一次方法执行开始的时间算起，如果上一次方法阻塞住了，下一次也是不会执行，但是在阻塞这段时间内累计应该执行的次数
     *    当不再阻塞时，一下子把这些全部执行掉，而后再按照固定速率继续执行。
     *
     * 3、cron表达式可以定制化执行任务，但是执行的方式是与fixedDelay相近的，也是会按照上一次方法结束时间开始算起。
     *
     */

    // 以下内容取消注释后可正常使用

/*    @Scheduled(fixedRate = FIVE_SECONDS)
    public void scheduledTask() {
        logger.info("The time is now {}", dateFormat.format(new Date()));
    }
    @Scheduled(fixedRate = ONE_DAY)
    public void scheduledTask1() {
        logger.info("我是一个每间隔一天执行一次的调度任务");
    }
    @Scheduled(fixedDelay  = ONE_HOURS)
    public void scheduledTask2() {
        logger.info("我是一个每间隔一小时执行一次的调度任务");
    }
    @Scheduled(fixedRate = TEN_SECONDS)
    public void scheduledTask3() { logger.info("我是一个每间隔十秒钟执行一次的调度任务"); }*/

}

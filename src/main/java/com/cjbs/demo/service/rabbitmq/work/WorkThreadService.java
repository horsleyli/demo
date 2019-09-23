package com.cjbs.demo.service.rabbitmq.work;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author shj
 */
@Service
public class WorkThreadService {

    private static final Logger logger = LoggerFactory.getLogger(WorkThreadService.class);

    private WorkReceive1 workReceive1;

    private WorkReceive2 workReceive2;

    public WorkThreadService(WorkReceive1 workReceive1, WorkReceive2 workReceive2) {
        this.workReceive1 = workReceive1;
        this.workReceive2 = workReceive2;
    }


    /**
     * # @Async多线程创建一个新线程
     * @throws Exception exception
     */
    @Async("taskExecutor")
    public void task1() throws Exception{
        logger.debug("WorkThreadService task1");
        workReceive1.workReceive();
    }

    /**
     * # @Async多线程创建一个新线程
     * @throws Exception exception
     */
    @Async("taskExecutor")
    public void task2() throws Exception{
        logger.debug("WorkThreadService task2");
        workReceive2.workReceive();
    }
}

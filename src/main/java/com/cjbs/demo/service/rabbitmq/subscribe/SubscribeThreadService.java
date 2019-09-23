package com.cjbs.demo.service.rabbitmq.subscribe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author shj
 */
@Service
public class SubscribeThreadService {

    private static final Logger logger = LoggerFactory.getLogger(SubscribeThreadService.class);

    private SubscribeReceive1 subscribeReceive1;

    private SubscribeReceive2 subscribeReceive2;

    public SubscribeThreadService(SubscribeReceive1 subscribeReceive1, SubscribeReceive2 subscribeReceive2) {
        this.subscribeReceive1 = subscribeReceive1;
        this.subscribeReceive2 = subscribeReceive2;
    }


    @Async("taskExecutor")
    public void task1() throws Exception{
        logger.debug("SubscribeThreadService task1");
        subscribeReceive1.subscribeReceive();
    }

    @Async("taskExecutor")
    public void task2() throws Exception{
        logger.debug("SubscribeThreadService task2");
        subscribeReceive2.subscribeReceive();
    }
}

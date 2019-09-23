package com.cjbs.demo.service.rabbitmq.topic;

import com.cjbs.demo.service.rabbitmq.route.RouteReceive1;
import com.cjbs.demo.service.rabbitmq.route.RouteReceive2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author shj
 */
@Service
public class TopicThreadService {

    private static final Logger logger = LoggerFactory.getLogger(TopicThreadService.class);

    private TopicReceive1 topicReceive1;

    private TopicReceive2 topicReceive2;

    public TopicThreadService(TopicReceive1 topicReceive1, TopicReceive2 topicReceive2) {
        this.topicReceive1 = topicReceive1;
        this.topicReceive2 = topicReceive2;
    }


    @Async("taskExecutor")
    public void task1() throws Exception{
        logger.debug("RouteThreadService task1");
        topicReceive1.topicReceive();
    }

    @Async("taskExecutor")
    public void task2() throws Exception{
        logger.debug("RouteThreadService task2");
        topicReceive2.topicReceive();
    }
}

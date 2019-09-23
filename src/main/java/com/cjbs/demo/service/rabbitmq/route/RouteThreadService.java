package com.cjbs.demo.service.rabbitmq.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author shj
 */
@Service
public class RouteThreadService {

    private static final Logger logger = LoggerFactory.getLogger(RouteThreadService.class);

    private RouteReceive1 routeReceive1;

    private RouteReceive2 routeReceive2;

    public RouteThreadService(RouteReceive1 routeReceive1, RouteReceive2 routeReceive2) {
        this.routeReceive1 = routeReceive1;
        this.routeReceive2 = routeReceive2;
    }


    @Async("taskExecutor")
    public void task1() throws Exception{
        logger.debug("RouteThreadService task1");
        routeReceive1.routeReceive();
    }

    @Async("taskExecutor")
    public void task2() throws Exception{
        logger.debug("RouteThreadService task2");
        routeReceive2.routeReceive();
    }
}

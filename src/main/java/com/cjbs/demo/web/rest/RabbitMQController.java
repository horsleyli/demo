package com.cjbs.demo.web.rest;


import com.cjbs.demo.service.rabbitmq.Send;
import com.cjbs.demo.service.rabbitmq.SimpleReceive;
import com.cjbs.demo.service.rabbitmq.route.RouteSend;
import com.cjbs.demo.service.rabbitmq.route.RouteThreadService;
import com.cjbs.demo.service.rabbitmq.subscribe.SubscribeSend;
import com.cjbs.demo.service.rabbitmq.subscribe.SubscribeThreadService;
import com.cjbs.demo.service.rabbitmq.topic.TopicSend;
import com.cjbs.demo.service.rabbitmq.topic.TopicThreadService;
import com.cjbs.demo.service.rabbitmq.work.WorkSend;
import com.cjbs.demo.service.rabbitmq.work.WorkThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shj
 */
@RestController
@RequestMapping("/api")
public class RabbitMQController {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQController.class);

    private Send send;

    private SimpleReceive simpleReceive;

    private WorkSend workSend;

    private WorkThreadService workThreadService;

    private SubscribeSend subscribeSend;

    private SubscribeThreadService subscribeThreadService;

    private RouteSend routeSend;

    private RouteThreadService routeThreadService;

    private TopicSend topicSend;

    private TopicThreadService topicThreadService;

    public RabbitMQController(Send send, SimpleReceive simpleReceive, WorkSend workSend, WorkThreadService workThreadService,
                              SubscribeSend subscribeSend, SubscribeThreadService subscribeThreadService, RouteSend routeSend,
                              RouteThreadService routeThreadService, TopicSend topicSend, TopicThreadService topicThreadService) {
        this.send = send;
        this.simpleReceive = simpleReceive;
        this.workSend = workSend;
        this.workThreadService = workThreadService;
        this.subscribeSend = subscribeSend;
        this.subscribeThreadService = subscribeThreadService;
        this.routeSend = routeSend;
        this.routeThreadService= routeThreadService;
        this.topicSend = topicSend;
        this.topicThreadService = topicThreadService;
    }

    /**
     * 生产者产生消息
     * @return Void
     * @throws Exception exception
     */
    @GetMapping("/sendMessage")
    public ResponseEntity<Void> sendMessage() throws Exception {
        logger.debug("RabbitMQController sendMessage");
        send.sendMessage();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 简单队列消费者
     * @return Void
     * @throws Exception exception
     */
    @GetMapping("/simpleReceive")
    public ResponseEntity<Void> simpleReceive() throws Exception {
        logger.debug("RabbitMQController simpleReceive");
        simpleReceive.ReceiveMessage();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * work模式生产者产生消息
     * @return Void
     * @throws Exception exception
     */
    @GetMapping("/sendMessageWork")
    public ResponseEntity<Void> sendMessageWork() throws Exception {
        logger.debug("RabbitMQController sendMessageWork");
        workSend.sendMessageWork();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Work模式消费者
     * @throws Exception exception
     */
    @GetMapping("/workReceive")
    public void workReceive() throws Exception {
        logger.debug("RabbitMQController workReceive");
        workThreadService.task1();
        workThreadService.task2();
    }

    /**
     * subscribe订阅模式生产者产生消息
     * @return Void
     * @throws Exception exception
     */
    @GetMapping("/sendMessageSubscribe")
    public ResponseEntity<Void> sendMessageSubscribe() throws Exception {
        logger.debug("RabbitMQController sendMessageSubscribe");
        subscribeSend.subscribeSend();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * subscribe订阅模式消费者
     * @throws Exception exception
     */
    @GetMapping("/subscribeReceive")
    public void subscribeReceive() throws Exception {
        logger.debug("RabbitMQController subscribeReceive");
        subscribeThreadService.task1();
        subscribeThreadService.task2();
    }

    /**
     * route路由模式生产者产生消息
     * @return Void
     * @throws Exception exception
     */
    @GetMapping("/sendMessageRoute")
    public ResponseEntity<Void> sendMessageRoute() throws Exception {
        logger.debug("RabbitMQController sendMessageRoute");
        routeSend.routeSend();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * route路由模式消费者
     * @throws Exception exception
     */
    @GetMapping("/routeReceive")
    public void routeReceive() throws Exception {
        logger.debug("RabbitMQController routeReceive");
        routeThreadService.task1();
        routeThreadService.task2();
    }

    /**
     * Topic通配符模式生产者产生消息
     * @return Void
     * @throws Exception exception
     */
    @GetMapping("/sendMessageTopic")
    public ResponseEntity<Void> sendMessageTopic() throws Exception {
        logger.debug("RabbitMQController sendMessageTopic");
        topicSend.topicSend();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Topic通配符模式消费者
     * @throws Exception exception
     */
    @GetMapping("/topicReceive")
    public void topicReceive() throws Exception {
        logger.debug("RabbitMQController topicReceive");
        topicThreadService.task1();
        topicThreadService.task2();
    }
}

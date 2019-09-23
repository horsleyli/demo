package com.cjbs.demo.service.rabbitmq.topic;

import com.cjbs.demo.service.utils.RabbitMQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Service;

/**
 * 主题模式(通配符模式)
 * @author shj
 */
@Service
public class TopicSend {

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public void topicSend() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        // 消息内容
        String message1 = "route.1";
        String message2 = "route.1.1";

        channel.basicPublish(EXCHANGE_NAME, "routekey.1", null, message1.getBytes());
        System.out.println(" [x] Sent '" + message1 + "'");
        channel.basicPublish(EXCHANGE_NAME, "routekey.1.1", null, message2.getBytes());
        System.out.println(" [x] Sent '" + message2 + "'");

        channel.close();
        connection.close();
    }
}

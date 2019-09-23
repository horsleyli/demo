package com.cjbs.demo.service.rabbitmq.subscribe;

import com.cjbs.demo.service.utils.RabbitMQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Service;

/**
 * 订阅模式
 * @author shj
 */
@Service
public class SubscribeSend {

    private final static String EXCHANGE_NAME = "test_exchange_fanout";


    public void subscribeSend() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange  fanout模式
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 消息内容
        String message = "Hello World!";


        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}

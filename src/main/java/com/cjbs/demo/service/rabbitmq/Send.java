package com.cjbs.demo.service.rabbitmq;

import com.cjbs.demo.service.utils.RabbitMQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Service;

/**
 * 生产者
 * @author shj
 */
@Service
public class Send {

    private final static String QUEUE_NAME = "q_test_01";

    /**
     * 产生消息
     * @throws Exception exception
     */
    public void sendMessage()throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitMQConnectionUtils.getConnection();

        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 消息内容
        String message = "Hello World!";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        //关闭通道和连接
        channel.close();
        connection.close();
    }

}

package com.cjbs.demo.service.rabbitmq;

import com.cjbs.demo.service.utils.RabbitMQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.stereotype.Service;

/**
 * 简单队列消费者
 * @author shj
 */
@Service
public class SimpleReceive {

    private final static String QUEUE_NAME = "q_test_01";

    public void ReceiveMessage() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitMQConnectionUtils.getConnection();

        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }
}

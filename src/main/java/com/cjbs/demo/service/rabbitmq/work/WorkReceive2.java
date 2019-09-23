package com.cjbs.demo.service.rabbitmq.work;


import com.cjbs.demo.service.utils.RabbitMQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.stereotype.Service;

/**
 * Work模式消费者2
 * @author shj
 */
@Service
public class WorkReceive2 {

    private final static String QUEUE_NAME = "test_queue_work";

    public void workReceive() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        QueueingConsumer consumerY = new QueueingConsumer(channel);

        // 监听队列，false表示手动返回完成状态，true表示自动
        channel.basicConsume(QUEUE_NAME, false, consumerY);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumerY.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [y] Received '" + message + "'");
            // 休眠1秒
            Thread.sleep(5000);
            //下面这行注释掉表示使用自动确认模式
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}

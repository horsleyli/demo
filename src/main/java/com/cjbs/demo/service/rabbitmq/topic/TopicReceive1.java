package com.cjbs.demo.service.rabbitmq.topic;

import com.cjbs.demo.service.utils.RabbitMQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import org.springframework.stereotype.Service;

/**
 * 通配符模式消费者1
 *
 * 将路由键和某模式进行匹配。此时队列需要绑定一个模式上，符号"#"匹配一个或多个词，符号"*"匹配一个词。
 * 因此，"audit.#"能够匹配到"audit.irs.corporate",但是"audit.*"只会匹配到"audit.irs"。
 * @author shj
 */
@Service
public class TopicReceive1 {

    private final static String QUEUE_NAME = "test_queue_topic1";

    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public void topicReceive() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "routekey.*");

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [Recv_x] Received '" + message + "'");
            Thread.sleep(10);

            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}

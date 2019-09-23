package com.cjbs.demo.service.rabbitmq.work;

import com.cjbs.demo.service.utils.RabbitMQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Service;

/**
 * 生产者
 *
 * https://blog.csdn.net/hellozpc/article/details/81436980
 * @author shj
 */
@Service
public class WorkSend {

    private final static String QUEUE_NAME = "test_queue_work";

    /**
     * Work模式测试，产生100条消息
     * @throws Exception exception
     */
    public void sendMessageWork() throws Exception {
        // 获取到连接以及mq通道
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        for (int i = 0; i < 20; i++) {
            // 消息内容
            String message = "" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");

            //Thread.sleep(i * 10);
        }

        channel.close();
        connection.close();
    }
}

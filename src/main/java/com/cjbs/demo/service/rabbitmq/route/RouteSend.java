package com.cjbs.demo.service.rabbitmq.route;

import com.cjbs.demo.service.utils.RabbitMQConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.stereotype.Service;

/**
 * 路由模式
 * @author shj
 */
@Service
public class RouteSend {

    private final static String EXCHANGE_NAME = "test_exchange_direct";


    public void routeSend() throws Exception {

        // 获取到连接以及mq通道
        Connection connection = RabbitMQConnectionUtils.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange  direct模式
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 消息内容
        String messageInsert = "添加商品";
        String messageUpdate = "修改商品";
        String messageDelete1 = "删除商品1";
        String messageDelete2 = "删除商品2";


        channel.basicPublish(EXCHANGE_NAME, "insert", null, messageInsert.getBytes());
        System.out.println(" [x] Sent '" + messageInsert + "'");
        channel.basicPublish(EXCHANGE_NAME, "update", null, messageUpdate.getBytes());
        System.out.println(" [x] Sent '" + messageUpdate + "'");
        channel.basicPublish(EXCHANGE_NAME, "delete", null, messageDelete1.getBytes());
        System.out.println(" [x] Sent '" + messageDelete1 + "'");
        channel.basicPublish(EXCHANGE_NAME, "delete", null, messageDelete2.getBytes());
        System.out.println(" [x] Sent '" + messageDelete2 + "'");

        channel.close();
        connection.close();
    }
}

package cn.zzk.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("root");
        factory.setPassword("root");
        factory.setVirtualHost("/");

        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection("生产者");
            channel = connection.createChannel();

            /*
                1. 队列名称
                2. 队列是否持久化。
                3. 队列是否具有排他性
                4. 队列是否自动删除。随着最后一个消息消费完毕以后队列是否自动删除
                5. 携带附加参数
             */
            String queue = "队列一";
            channel.queueDeclare(queue, false, false, false, null);

            String message = "Hello World!";
            channel.basicPublish("", queue, null, message.getBytes());
            System.out.println("发送消息");

        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException | TimeoutException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

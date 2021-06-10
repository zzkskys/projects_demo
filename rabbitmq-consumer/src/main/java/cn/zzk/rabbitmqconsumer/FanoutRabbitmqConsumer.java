package cn.zzk.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class FanoutRabbitmqConsumer {

    @RabbitListener(queues = "sms")
    public void smsConsumer(String message) {
        System.out.println("fanout 模式 sms 队列接收到消息 : " +message);
    }

    @RabbitListener(queues = "email")
    public void emailConsumer(String message) {
        System.out.println("fanout 模式 email 队列接收到消息 : " + message);
    }
}

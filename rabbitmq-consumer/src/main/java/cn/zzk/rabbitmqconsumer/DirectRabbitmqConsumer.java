package cn.zzk.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectRabbitmqConsumer {

    @RabbitListener(queues = "sms.direct")
    public void smsConsumer(String message) {
        System.out.println("direct 模式 sms.direct 队列接收到消息 : " + message);
    }

    @RabbitListener(queues = "email.direct")
    public void emailConsumer(String message) {
        System.out.println("direct 模式 email.direct 队列接收到消息 : " + message);
    }
}

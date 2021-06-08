package cn.zzk.rabbitmqconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class RabbitConsumer {

    @RabbitListener(queues = "sms")
    public void smsConsumer(String message) {
        System.out.println(message);
    }

    @RabbitListener(queues = "email")
    public void emailConsumer(String message) {
        System.out.println(message);
    }
}

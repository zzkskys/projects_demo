package cn.zzk.rabbitmqconsumer.ttl;

import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TTLConsumer {

    @RabbitListener(queuesToDeclare = @Queue(value = "ttl.test", arguments = @Argument(name = "x-message-ttl", value = "5000", type = "java.lang.Integer")))
    public void ttlConsumer(String message) {

    }
}

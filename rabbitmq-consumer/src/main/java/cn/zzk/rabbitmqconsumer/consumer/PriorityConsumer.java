package cn.zzk.rabbitmqconsumer.consumer;

import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PriorityConsumer {


    @RabbitListener(queuesToDeclare = @Queue(
            value = "quick.queue",
            durable = "true",
            arguments = {
                    @Argument(name = "x-max-priority", value = "10", type = "java.lang.Integer")
            })
    )
    public void quick(String message) {
        System.out.println("message : " + message);
    }


    @RabbitListener(queuesToDeclare = @Queue(
            value = "slow.queue",
            durable = "true",
            arguments = {
                    @Argument(name = "x-max-priority", value = "1", type = "java.lang.Integer")
            })
    )
    public void slow(String message) {
        System.out.println("message : " + message);
    }
}

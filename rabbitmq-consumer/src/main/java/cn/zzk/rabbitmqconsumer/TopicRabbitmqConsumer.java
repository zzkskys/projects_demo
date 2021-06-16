package cn.zzk.rabbitmqconsumer;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicRabbitmqConsumer {

    public static final String EXCHANGE_NAME = "order.topic";

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "sms.topic"),
                    exchange = @Exchange(value = EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
                    key = "#.sms.#"
            )
    })
    public void sms(String message) {
        System.out.println("topic 模式 sms 队列接收到消息 : " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = "email.topic"),
                    exchange = @Exchange(value = EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
                    key = "#.email.#"
            )
    })
    public void email(String message) {
        System.out.println("topic 模式 message 队列接收到消息 : " + message);
    }
}

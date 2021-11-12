package cn.zzk.rabbitmqconsumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 手动应答
 */
@Component
@Slf4j
public class AskConsumer {

    @RabbitListener(queues = "ask.queue")
    public void askMessage(AskMessage message) {
        if (message.getI() % 5 == 0) {
//            throw new RuntimeException("应答消息,是 5 的倍数 , i : " + message.getI());
            log.error("应答消息,是 5 的倍数 , i : {}",message.getI());
        }else {
            log.info("应答消息 , message : {} , i : {}", message.getContent(),message.getI());
        }
    }
}

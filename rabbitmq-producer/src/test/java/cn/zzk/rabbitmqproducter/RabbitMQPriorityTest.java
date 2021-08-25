package cn.zzk.rabbitmqproducter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled
public class RabbitMQPriorityTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void priority() {
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("quick.queue", "快速消费");
        }
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("slow.queue", "慢消费");
        }
    }
}

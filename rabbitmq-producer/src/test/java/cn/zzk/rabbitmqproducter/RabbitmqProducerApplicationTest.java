package cn.zzk.rabbitmqproducter;

import cn.zzk.rabbitmqproducter.direct.DirectRabbitmqConfig;
import cn.zzk.rabbitmqproducter.fanout.FanoutRabbitmqConfig;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Disabled
class RabbitmqProducerApplicationTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Test
    void notNullTest() {
        assertNotNull(rabbitTemplate);
    }

    @DisplayName("广播模式测试")
    @Test
    void fanoutTest() {
        String message = "某用户下单了, userId : " + UUID.randomUUID();
        String routingKey = "";
        rabbitTemplate.convertAndSend(FanoutRabbitmqConfig.ORDER_EXCHANGE, routingKey, message);
    }

    @DisplayName("直连模式测试")
    @Test
    void directTest() {
        String message = "某用户下单了, userId : " + UUID.randomUUID();
        rabbitTemplate.convertAndSend(DirectRabbitmqConfig.EXCHANGE_NAME, "email", message);
        rabbitTemplate.convertAndSend(DirectRabbitmqConfig.EXCHANGE_NAME, "sms", message);
    }

    @DisplayName("主题模式测试")
    @Test
    void topicTest() {
        rabbitTemplate.convertAndSend("order.topic", "email.1", "111");
        rabbitTemplate.convertAndSend("order.topic", "sms.1", "2222");
        rabbitTemplate.convertAndSend("order.topic", "1.sms.email.1", "3333");
    }

    @DisplayName("发送接收 json 测试")
    @Test
    void jsonTest() {
        rabbitTemplate.convertAndSend("json.test", new Person().setId("1").setName("张三").setAge(20));
    }

    @DisplayName("ttl 测试")
    @Test
    void ttlTest() {
        rabbitTemplate.convertAndSend("ttl.test", "hello");
        rabbitTemplate.convertAndSend("ttl.test", "hello", (message -> {
            MessageProperties properties = message.getMessageProperties();
            properties.setExpiration("3000");
            properties.setContentEncoding("UTF-8");
            return message;
        }));
    }

    @DisplayName("死信队列测试")
    @Test
    void ttlDeadTest() {
        rabbitTemplate.convertAndSend("ttl.dead", "hello");
    }

    @Data
    @Accessors(chain = true)
    public static class Person {
        private String id;
        private String name;
        private int age;
    }

}
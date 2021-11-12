package com.example.consumer;

import cn.zzk.rabbitmqconsumer.AskMessage;
import cn.zzk.rabbitmqconsumer.config.RabbitmqConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Disabled
class AskConsumerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void askMessage() {
        assertNotNull(rabbitTemplate);
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            rabbitTemplate.convertAndSend("ask.queue", new AskMessage().setContent("hello").setI(i));
        }
    }
}
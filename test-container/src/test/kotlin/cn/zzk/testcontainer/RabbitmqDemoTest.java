package cn.zzk.testcontainer;

import cn.zzk.testcontainer.init.RabbitInit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@Testcontainers
@Import({RabbitMQConfig.class, RabbitAutoConfiguration.class, HelloConsumer.class})
public class RabbitmqDemoTest extends RabbitInit {

    @MockBean
    private HelloApplicationService service;


    @Autowired
    private RabbitTemplate template;

    @Test
    void temp() {
        doNothing().when(service).sendHello(any());
        template.convertAndSend("hello", "Hello World!");
        verify(service, timeout(500)).sendHello(any());
    }
}

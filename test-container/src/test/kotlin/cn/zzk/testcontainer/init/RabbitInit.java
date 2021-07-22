package cn.zzk.testcontainer.init;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.utility.DockerImageName;

public class RabbitInit {

    public static final RabbitMQContainer mq = new RabbitMQContainer(DockerImageName.parse("rabbitmq").withTag("3.8.19-management-alpine"));

    static {
        mq.start();
    }

    @DynamicPropertySource
    public static void registerDynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.rabbitmq.host", mq::getHost);
        registry.add("spring.rabbitmq.port", mq::getAmqpPort);
        registry.add("spring.rabbitmq.username", mq::getAdminUsername);
        registry.add("spring.rabbitmq.password", mq::getAdminPassword);
    }

}

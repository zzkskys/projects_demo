package cn.zzk.rabbitmqconsumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试队列优先级
 */
@Configuration
public class PriorityQueueConfig {

    @Bean
    public Queue askQueue() {
        return new Queue("ask.queue", true, false, false);
    }

}

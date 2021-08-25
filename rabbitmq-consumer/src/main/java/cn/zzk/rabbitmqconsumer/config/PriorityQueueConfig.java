package cn.zzk.rabbitmqconsumer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 测试队列优先级
 */
@Configuration
public class PriorityQueueConfig {

//    @Bean
//    public Queue slowQueue() {
//        return new Queue("slow.queue", true);
//    }


//    @Bean
//    public Queue quickQueue() {
//        return QueueBuilder
//                .durable("quick.queue")
//                .maxPriority(10)
//                .ttl(1_000_000)
//                .maxLength(999)
//                .build();
//    }
}

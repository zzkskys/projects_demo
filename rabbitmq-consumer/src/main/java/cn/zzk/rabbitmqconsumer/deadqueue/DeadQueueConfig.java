package cn.zzk.rabbitmqconsumer.deadqueue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class DeadQueueConfig {


    /**
     * 过期队列测试。
     * 若队列的消息过期，则发送至死信队列
     */
    @Bean
    public Queue ttlDirectQueue() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 5000);
        args.put("x-dead-letter-exchange", "dead.direct");

        //配置发送到死信队列的路由 key
        args.put("x-dead-letter-routing-key", "dead");
        return new Queue("ttl.dead", true, false, false, args);
    }

    /**
     * 死信队列交换机
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("dead.direct");
    }


    /**
     * 死信队列
     */
    @Bean
    public Queue deadQueue() {
        return new Queue("dead.test", true);
    }

    @Bean
    public Binding deadBinds() {
        return BindingBuilder.bind(deadQueue()).to(directExchange()).with("dead");
    }

}

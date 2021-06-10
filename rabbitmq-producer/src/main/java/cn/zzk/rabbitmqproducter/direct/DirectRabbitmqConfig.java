package cn.zzk.rabbitmqproducter.direct;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitmqConfig {

    public static final String EXCHANGE_NAME = "order.direct";

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME, true, false);
    }

    @Bean
    public Queue smsDirectQueue() {
        return new Queue("sms.direct", true);
    }

    @Bean
    public Queue emailDirectQueue() {
        return new Queue("email.direct", true);
    }


    @Bean
    public Binding smsDirectBinding() {
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with(DirectRoutingKey.SMS.getKey());
    }

    @Bean
    public Binding emailDirectBinding() {
        return BindingBuilder.bind(emailDirectQueue()).to(directExchange()).with(DirectRoutingKey.EMAIL.getKey());
    }


}

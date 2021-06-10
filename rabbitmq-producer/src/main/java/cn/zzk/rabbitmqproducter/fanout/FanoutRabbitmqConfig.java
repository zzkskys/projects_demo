package cn.zzk.rabbitmqproducter.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitmqConfig {

    /**
     * 订单交换机,用于订单的生成
     */
    public static final String ORDER_EXCHANGE = "order.fanout";

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(ORDER_EXCHANGE, true, false);
    }

    /**
     * 短信队列
     */
    @Bean
    public Queue smsQueue() {
        return new Queue("sms", true);
    }

    /**
     * 邮件队列
     */
    @Bean
    public Queue emailQueue() {
        return new Queue("email", true);
    }

    @Bean
    public Binding smsBinding() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }

}

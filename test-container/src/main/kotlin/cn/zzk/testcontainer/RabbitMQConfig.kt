package cn.zzk.testcontainer

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Bean
    fun helloQueue(): Queue {
        return Queue("hello", true)
    }

}
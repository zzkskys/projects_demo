package cn.zzk.rabbitmqproducter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 考虑 Rabbitmq 断开连接的情况,则对 Rabbitmq 进行扩展
 */
@Component
@Slf4j
public class BosRabbitTemplate {

    private final RabbitTemplate rabbitTemplate;

    public BosRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a default exchange
     * with a default routing key.
     *
     * @param message a message to send
     * @throws AmqpException if there is a problem
     */
    public void convertAndSend(Object message) {
        rabbitTemplate.convertAndSend(message);
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a default exchange
     * with a specific routing key.
     *
     * @param routingKey the routing key
     * @param message    a message to send
     * @throws AmqpException if there is a problem
     */
    public void convertAndSend(String routingKey, Object message) {
        rabbitTemplate.convertAndSend(routingKey, message);
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a specific exchange
     * with a specific routing key.
     *
     * @param exchange   the name of the exchange
     * @param routingKey the routing key
     * @param message    a message to send
     * @throws AmqpException if there is a problem
     */
    public void convertAndSend(String exchange, String routingKey, Object message) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a default exchange
     * with a default routing key.
     *
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @throws AmqpException if there is a problem
     */
    public void convertAndSend(Object message, MessagePostProcessor messagePostProcessor) {
        rabbitTemplate.convertAndSend(message, messagePostProcessor);
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a default exchange
     * with a specific routing key.
     *
     * @param routingKey           the routing key
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @throws AmqpException if there is a problem
     */
    public void convertAndSend(String routingKey, Object message, MessagePostProcessor messagePostProcessor) {
        rabbitTemplate.convertAndSend(routingKey, message, messagePostProcessor);
    }

    /**
     * Convert a Java object to an Amqp {@link Message} and send it to a specific exchange
     * with a specific routing key.
     *
     * @param exchange             the name of the exchange
     * @param routingKey           the routing key
     * @param message              a message to send
     * @param messagePostProcessor a processor to apply to the message before it is sent
     * @throws AmqpException if there is a problem
     */
    public void convertAndSend(String exchange, String routingKey, Object message, MessagePostProcessor messagePostProcessor) {
        rabbitTemplate.convertAndSend(exchange, routingKey, message, messagePostProcessor);
    }


}

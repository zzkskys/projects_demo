package cn.zzk.rabbitmqproducter;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final RabbitTemplate rabbitTemplate;


    /**
     * fanout模式
     */
    public void generateOrder(String userId) {
        String message = "某用户下单了, userId : " + userId;
        String routingKey = "";
        rabbitTemplate.convertAndSend(RabbitmqConfig.ORDER_EXCHANGE, routingKey, message);
    }
}

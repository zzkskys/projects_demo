package cn.zzk.rabbitmqproducter.direct;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DirectOrderService {

    private final RabbitTemplate template;

    /**
     * fanout模式
     */
    public void generateOrder(String userId, DirectRoutingKey key) {
        String message = "某用户下单了, userId : " + userId;
        template.convertAndSend(DirectRabbitmqConfig.EXCHANGE_NAME, key.getKey(), message);
    }

}

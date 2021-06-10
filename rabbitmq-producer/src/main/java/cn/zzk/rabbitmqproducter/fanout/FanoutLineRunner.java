package cn.zzk.rabbitmqproducter.fanout;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class FanoutLineRunner implements CommandLineRunner {

    private final FanoutOrderService fanoutOrderService;


    @Override
    public void run(String... args) throws Exception {
        fanoutOrderService.generateOrder(UUID.randomUUID().toString());
        fanoutOrderService.generateOrder(UUID.randomUUID().toString());
    }
}

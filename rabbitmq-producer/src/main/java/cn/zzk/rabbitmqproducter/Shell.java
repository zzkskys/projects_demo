package cn.zzk.rabbitmqproducter;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class Shell implements CommandLineRunner {

    private final OrderService orderService;


    @Override
    public void run(String... args) throws Exception {
        orderService.generateOrder(UUID.randomUUID().toString());
        orderService.generateOrder(UUID.randomUUID().toString());
    }
}

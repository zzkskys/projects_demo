package cn.zzk.rabbitmqproducter.direct;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class DirectLineRunner implements CommandLineRunner {

    private final DirectOrderService service;


    @Override
    public void run(String... args) throws Exception {
        service.generateOrder(UUID.randomUUID().toString(), DirectRoutingKey.EMAIL);
        service.generateOrder(UUID.randomUUID().toString(), DirectRoutingKey.SMS);
    }
}

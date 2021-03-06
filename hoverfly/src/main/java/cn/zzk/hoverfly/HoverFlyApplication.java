package cn.zzk.hoverfly;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

//@EnableFeignClients
@SpringBootApplication
public class HoverFlyApplication {
    public static void main(String[] args) {
        SpringApplication.run(HoverFlyApplication.class, args);
    }


    @RestController
    @RequestMapping
    public static class HoverFlyController {
        @GetMapping
        public String hello() {
            return "hello";
        }


        @GetMapping("/uuid")
        public String uuid() {
            return UUID.randomUUID().toString();
        }
    }
}

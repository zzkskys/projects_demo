package cn.zzk.rabbitmqconsumer;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JsonConsumer {


    @RabbitListener(queuesToDeclare = @Queue("json.test"))
    public void jsonConsumer(Student student) {
        log.info("id : {} , name : {}",student.getId(),student.getName());
    }

    @Data
    public static class Student {
        private String id;
        private String name;
    }
}

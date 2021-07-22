package cn.zzk.testcontainer

import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Component
class HelloConsumer(
    private val service: HelloApplicationService
) {


    @RabbitListener(queues = ["hello"])
    fun hello(message: Message) {
        service.sendHello(String(message.body))
    }
}

@Service
class HelloApplicationService {

    fun sendHello(message: String) {
        println("消息内容 : ${message}")
    }
}
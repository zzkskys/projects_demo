package cn.zzk.dddsimple.infrastucture

import cn.zzk.dddsimple.domain.MessageCreatedEvent
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class RabbitMessageService {

    companion object {
        private val log = LoggerFactory.getLogger(RabbitMessageService::class.java)
    }

    @EventListener
    fun whenever(event: MessageCreatedEvent) {
        log.info("向 rabbitmq 发送消息")
    }
}
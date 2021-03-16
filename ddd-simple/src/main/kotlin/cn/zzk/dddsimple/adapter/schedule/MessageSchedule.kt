package cn.zzk.dddsimple.adapter.schedule

import cn.zzk.dddsimple.domain.Message
import cn.zzk.dddsimple.domain.MessageService
import cn.zzk.dddsimple.domain.Receiver
import cn.zzk.dddsimple.domain.UserRepo
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class MessageSchedule(
    private val userRepo: UserRepo,
    private val messageService: MessageService
) {

    @Scheduled(fixedDelayString = "#{messageProperties.rate}")
    fun createMessages() {
        val users = userRepo.findAll()
        users
            .map { Message(content = "Hello ${it.name}", receiver = Receiver(it)) }
            .forEach { messageService.createMessage(it) }
    }

}
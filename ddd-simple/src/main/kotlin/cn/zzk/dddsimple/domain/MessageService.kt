package cn.zzk.dddsimple.domain

import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val messageRepo: MessageRepo,
    private val userRepo: UserRepo,
    private val publisher: ApplicationContext
) {

    fun createMessage(message: Message): Message {
        if (!userRepo.existsById(message.receiver.receiverId)) {
            throw IllegalArgumentException("接收人必须存在")
        }
        messageRepo.save(message)
        publisher.publishEvent(MessageCreatedEvent(message))
        return message
    }
}
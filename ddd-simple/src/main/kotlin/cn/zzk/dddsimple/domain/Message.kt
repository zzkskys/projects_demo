package cn.zzk.dddsimple.domain

import cn.zzk.dddsimple.infrastucture.persist.ReceiverConverter
import java.util.*
import javax.persistence.Convert
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class Message(

    var content: String = "",

    var readed: Boolean = false,

    @Convert(converter = ReceiverConverter::class)
    var receiver: Receiver,

    @Id
    val id: String = UUID.randomUUID().toString(),
)


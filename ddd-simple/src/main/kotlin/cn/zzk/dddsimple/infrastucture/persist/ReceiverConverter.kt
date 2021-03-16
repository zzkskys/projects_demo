package cn.zzk.dddsimple.infrastucture.persist

import cn.zzk.dddsimple.domain.Receiver
import javax.persistence.AttributeConverter

class ReceiverConverter : AttributeConverter<Receiver, String> {
    override fun convertToDatabaseColumn(attribute: Receiver): String {
        return attribute.receiverId
    }

    override fun convertToEntityAttribute(dbData: String): Receiver {
        return Receiver(dbData)
    }
}
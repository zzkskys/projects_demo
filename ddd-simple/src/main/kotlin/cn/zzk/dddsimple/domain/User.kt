package cn.zzk.dddsimple.domain

import org.springframework.data.domain.AfterDomainEventPublication
import org.springframework.data.domain.DomainEvents
import java.util.*
import javax.persistence.*


@Entity
class User(
    name: String = "",

    @Enumerated(EnumType.STRING)
    val role: Role = Role.USER,

    @Id
    val id: String = UUID.randomUUID().toString()
) {

    @Transient
    private val events: MutableCollection<Any> = mutableListOf()

    var name: String = name
        set(value) {
            if (value.isBlank()) {
                throw IllegalArgumentException("姓名不能为null")
            }
            val event = NameChanged(this.id, field, value)
            field = value
            publishEvent(event)
        }

    fun publishEvent(event: Any) {
        this.events.add(event)
    }

    @DomainEvents
    fun domainEvents() = events

    @AfterDomainEventPublication
    fun clearEvent() {
        this.events.clear()
    }


    /**
     * 枚举，若该枚举归属某个类则放在内部类中
     */
    enum class Role {
        ADMIN,

        USER
    }
}
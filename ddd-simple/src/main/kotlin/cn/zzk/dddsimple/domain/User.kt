package cn.zzk.dddsimple.domain

import java.util.*
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id


@Entity
class User(
    name: String = "",

    @Enumerated(EnumType.STRING)
    val role: Role = Role.USER,

    @Id
    val id: String = UUID.randomUUID().toString()
) {

    var name: String = name
        set(value) {
            if (value.isBlank()) {
                throw IllegalArgumentException("姓名不能为null")
            }
            field = value
        }


    /**
     * 枚举，若该枚举归属某个类则放在内部类中
     */
    enum class Role {
        ADMIN,

        USER
    }
}
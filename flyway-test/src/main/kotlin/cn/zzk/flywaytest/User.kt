package cn.zzk.flywaytest

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(

    var name: String = "",

    var createTime:LocalDateTime = LocalDateTime.now(),

    @Id
    var id: String = UUID.randomUUID().toString(),
)
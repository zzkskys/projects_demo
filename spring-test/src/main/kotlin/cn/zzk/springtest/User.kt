package cn.zzk.springtest

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(

    @Id
    var id: String = UUID.randomUUID().toString(),

    var name: String = "",

    var age: Int = 0
)
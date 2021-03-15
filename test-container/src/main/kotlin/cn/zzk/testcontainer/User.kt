package cn.zzk.testcontainer

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class User(

    var name: String = "",

    var age: Int = 18,

    @Id
    val id: String = UUID.randomUUID().toString(),
)
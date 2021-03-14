package cn.zzk.springtest

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class OrganUnit(

    val name:String,

    @Id
    val id:String = UUID.randomUUID().toString(),
)
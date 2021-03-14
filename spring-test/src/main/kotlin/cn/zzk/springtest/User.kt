package cn.zzk.springtest

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class User(

    var name: String = "",

    var age: Int = 0,

    @Id
    var id: String = UUID.randomUUID().toString(),

    organUnit: OrganUnit? = null
) {

    @ManyToOne
    lateinit var organUnit: OrganUnit

    init {
        if (organUnit != null) {
            this.organUnit = organUnit
        }
    }
}
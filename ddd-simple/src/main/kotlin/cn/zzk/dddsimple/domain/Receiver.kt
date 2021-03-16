package cn.zzk.dddsimple.domain

import java.io.Serializable

class Receiver(
    var receiverId: String
) : Serializable {
    constructor(user: User) : this(user.id)
}
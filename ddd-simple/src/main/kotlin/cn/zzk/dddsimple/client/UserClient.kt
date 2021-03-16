package cn.zzk.dddsimple.client

import cn.zzk.dddsimple.domain.User

interface UserClient {

    fun findUsers(): List<User>
}
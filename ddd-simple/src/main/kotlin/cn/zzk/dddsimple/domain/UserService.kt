package cn.zzk.dddsimple.domain

import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class UserService {

    @EventListener
    fun whenever(event: UserSaved) {
        println("保存一个用户")
    }

    @EventListener
    fun whenever(event: NameChanged) {
        println("更改用户姓名")
    }
}
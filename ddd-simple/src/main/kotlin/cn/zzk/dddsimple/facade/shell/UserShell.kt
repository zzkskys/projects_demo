package cn.zzk.dddsimple.facade.shell

import cn.zzk.dddsimple.application.UserAppService
import cn.zzk.dddsimple.domain.User
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class UserShell(
    private val userAppService: UserAppService
) {

    @Bean
    fun addUserShell() = CommandLineRunner {
        userAppService.add(User("张三"))
    }

    @Bean
    fun updateName() = CommandLineRunner {
        val u1 = userAppService.add(User("a"))
        userAppService.changeName(u1.id, "b")
    }

}
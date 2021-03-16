package cn.zzk.dddsimple.adapter.shell

import cn.zzk.dddsimple.domain.User
import cn.zzk.dddsimple.domain.UserRepo
import cn.zzk.dddsimple.domain.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class UserShell(
    private val userService: UserService,
    private val userRepo: UserRepo
) : CommandLineRunner {

    override fun run(vararg args: String) {
        if (userRepo.count() != 0L) {
            userService.add(User(role = User.Role.ADMIN, name = "系统管理员"))
        }
    }
}
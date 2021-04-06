package cn.zzk.dddsimple.facade.controller

import cn.zzk.dddsimple.application.UserAppService
import cn.zzk.dddsimple.domain.User
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users")
class UserController(
    val userAppService: UserAppService
) {

    @PostMapping
    fun addUser(@RequestBody user: User): User {
        return userAppService.add(user)
    }
}
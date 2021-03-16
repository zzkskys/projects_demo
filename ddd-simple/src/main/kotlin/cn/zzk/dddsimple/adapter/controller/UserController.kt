package cn.zzk.dddsimple.adapter.controller

import cn.zzk.dddsimple.domain.User
import cn.zzk.dddsimple.domain.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/users")
class UserController(
    val userService: UserService
) {

    @PostMapping
    fun addUser(@RequestBody user: User): User {
        return userService.add(user)
    }
}
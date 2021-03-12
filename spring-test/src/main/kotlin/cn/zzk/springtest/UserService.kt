package cn.zzk.springtest

import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class UserService(
    private val userRepo: UserRepo
) {

    fun save(user: User):User{
        if (userRepo.existsByName(user.name)) {
            throw IllegalArgumentException("用户名称不能重复")
        }
        return userRepo.save(user)
    }
}
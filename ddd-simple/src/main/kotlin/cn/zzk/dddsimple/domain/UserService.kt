package cn.zzk.dddsimple.domain

import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepo: UserRepo
) {

    fun add(user: User): User {
        if (userRepo.existsByName(user.name)) {
            throw IllegalArgumentException("用户名不可重复")
        }
        if (userRepo.existsById(user.id)) {
            throw IllegalArgumentException("用户 id 不可重复")
        }
        return userRepo.save(user)
    }
}
package cn.zzk.dddsimple.application

import cn.zzk.dddsimple.domain.User
import cn.zzk.dddsimple.domain.UserRepo
import org.springframework.stereotype.Service

@Service
class UserAppService(
    private val userRepo: UserRepo,
    private val userSavedProducer: UserSavedProducer
) {

    fun add(user: User): User {
        if (userRepo.existsByName(user.name)) {
            throw IllegalArgumentException("用户名不可重复")
        }
        if (userRepo.existsById(user.id)) {
            throw IllegalArgumentException("用户 id 不可重复")
        }
        val save = userRepo.save(user)
//        userSavedProducer.userSavedSend(UserSaved(user))
        return save
    }

    fun changeName(userId: String, name: String) {
        val user = userRepo.findById(userId).orElseThrow { IllegalArgumentException("用户不存在") }
        user.name = name
        userRepo.save(user)
    }
}
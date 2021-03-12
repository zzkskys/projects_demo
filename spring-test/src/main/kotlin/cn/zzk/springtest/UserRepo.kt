package cn.zzk.springtest

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo : JpaRepository<User, String> {

    fun existsByName(name: String): Boolean
}
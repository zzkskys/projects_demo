package cn.zzk.dddsimple.domain

import org.springframework.data.jpa.repository.JpaRepository


interface UserRepo : JpaRepository<User, String> {

    fun existsByName(name: String): Boolean

}
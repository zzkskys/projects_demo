package cn.zzk.testcontainer

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepo : JpaRepository<User, String> {

    @Query(nativeQuery = true, value = "select any_value(id) as id,name,age from user")
    fun findUsers(): List<User>
}
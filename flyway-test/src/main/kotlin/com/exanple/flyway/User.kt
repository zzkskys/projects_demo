package com.exanple.flyway

import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity
open class User(

        open var name: String = "",

        open var createTime: LocalDateTime = LocalDateTime.now(),

        @Id
        open var id: String = UUID.randomUUID().toString(),

        @Column(insertable = false, updatable = false)
        open var dtype: String? = null
)

interface UserRepo : JpaRepository<User, String>

@Entity
class Student(
        var idCard: String = "",

        name: String = "",

        createTime: LocalDateTime = LocalDateTime.now(),

        id: String = UUID.randomUUID().toString(),

        dtype: String? = null
) : User(name, createTime, id, dtype)


interface StudentRepo : JpaRepository<Student, String>
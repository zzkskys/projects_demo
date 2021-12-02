package com.exanple.flyway

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class UserController(
        private val userRepo: UserRepo,
        private val studentRepo: StudentRepo
) {

    @PostMapping("/users")
    fun addUser(): User {
        return userRepo.save(User("a"))
    }

    @GetMapping("/users")
    fun getUsers() = userRepo.findAll()

    @PostMapping("/students")
    fun addStudent(): Student {
        return studentRepo.save(Student(idCard = "aaa", name = "bb"))
    }

    @GetMapping("/students")
    fun getStudents() = studentRepo.findAll()

    @PostMapping("/mutil-save")
    fun muteSave() {
        userRepo.save(Student(idCard = "a", name = "user"))
    }
}
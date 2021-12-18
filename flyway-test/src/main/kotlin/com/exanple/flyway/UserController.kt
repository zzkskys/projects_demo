package com.exanple.flyway

import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class UserController(
    private val userRepo: UserRepo,
    private val studentRepo: StudentRepo,
    private val contactRepo: ContactRepo
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

    @PostMapping("/contacts")
    fun addContact(
        phone: String,
        userId: String,
        name: String? = null,
        email: String? = null
    ): Contact {
        val user = userRepo.findByIdOrNull(userId)
        val contact = Contact(phone = phone, email = email ?: "", user = user)
        if (name != null) {
            user?.name = name
        }
        return contactRepo.save(contact)
    }
}
package cn.zzk.springtest

import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserServiceTest {

    lateinit var userService: UserService

    @MockK
    lateinit var userRepo: UserRepo

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        userService = UserService(userRepo)
    }

    @Test
    fun save() {
        every { userRepo.existsByName("张三") } returns true
        every { userRepo.existsByName("李四") } returns false
        every { userRepo.save(any()) } returns User()


        assertThrows<IllegalArgumentException> { userService.save(User(name = "张三")) }

        userService.save(User(name = "李四"))
        verify { userRepo.save(any()) }

    }

}

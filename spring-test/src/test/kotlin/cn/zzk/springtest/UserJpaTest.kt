package cn.zzk.springtest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.jdbc.Sql

@DataJpaTest
class UserJpaTest {

    @Autowired
    lateinit var userRepo: UserRepo

    @Autowired
    lateinit var entityManager: TestEntityManager

    @Test
    fun notNull() {
        entityManager.merge(User(name = "张三", age = 18))
        assertNotNull(userRepo)
        assertEquals(1, userRepo.count())
    }

    @Test
    @Sql(
        statements = [
            "insert into user (id,name,age) values ('1','张三',18);"
        ]
    )
    fun jdbcTest() {
        assertEquals(1, userRepo.count())
    }

}
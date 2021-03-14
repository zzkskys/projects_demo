package cn.zzk.springtest

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.jdbc.Sql

@DataJpaTest
class UserRepoTest {

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
            "insert into user (id,name,age) values ('1','张三',18)"
        ]
    )
    fun jdbcTest() {
        assertEquals(1, userRepo.count())
    }

    @Test
    @Sql(
        statements = [
            "insert into organ_unit (id,name) values ('1','温州市局')",
            "insert into user (id,name,age,organ_unit_id) values ('1','张三',18,'1')",
        ]
    )
    @DisplayName("dto 查询")
    fun findUserDTOs() {
        val users = userRepo.findAllUserDTOs()
        assertEquals(1, users.size)
        assertEquals("1",users[0].id)
        assertEquals("温州市局",users[0].unitName)
    }

}
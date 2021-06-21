package cn.zzk.testcontainer

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.jdbc.Sql
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Testcontainers
class MysqlTestContainerDemoTest : MysqlInit()  {

    @Autowired
    lateinit var userRepo: UserRepo



    @RepeatedTest(value = 5)
    @Sql(
        statements = [
            "insert into user (id,name,age) values ('1','张三',18);"
        ]
    )
    fun findAll() {
        val users = userRepo.findUsers()
        assertEquals(1, users.size)
    }

}
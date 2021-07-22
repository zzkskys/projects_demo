package cn.zzk.testcontainer

import cn.zzk.testcontainer.init.MysqlInit
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.RepeatedTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import org.springframework.transaction.annotation.Transactional

@Transactional
@SpringBootTest
internal class TestContainerApplicationTest : MysqlInit() {


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
        Assertions.assertEquals(1, users.size)
    }
}
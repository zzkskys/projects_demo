package cn.zzk.flywaytest

import cn.zzk.flywaytest.upgrade.V2__Upgrade
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.jdbc.Sql

@ActiveProfiles("junit")
@DataJdbcTest
@ComponentScan(basePackageClasses = [V2__Upgrade::class])
class JdbcTest {

    @Autowired
    lateinit var jdbcTemplate: NamedParameterJdbcTemplate

    @Test
    @Sql(
        statements = [
            "insert into person (id,name) values ('1','张三')"
        ]
    )
    fun student() {
        val count =
            jdbcTemplate.queryForObject("select count(*) from person", emptyMap<String, Any>(), Long::class.java)
        assertEquals(1, count)
    }
}
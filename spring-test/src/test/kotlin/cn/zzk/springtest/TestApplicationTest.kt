package cn.zzk.springtest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class TestApplicationTest {

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @Test
    fun temp() {
        val json = """
            {
                "name":null,
                "id":null
            }
        """.trimIndent()

        objectMapper.readValue<Person>(json)
    }
}
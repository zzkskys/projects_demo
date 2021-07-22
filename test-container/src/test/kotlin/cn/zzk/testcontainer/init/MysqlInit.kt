package cn.zzk.testcontainer.init

import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

abstract class MysqlInit {
    companion object {
        val database = MySQLContainer<Nothing>(DockerImageName.parse("mysql").withTag("5.7.22"))

        init {
            database.start()
        }

        @DynamicPropertySource
        @JvmStatic
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", database::getJdbcUrl)
            registry.add("spring.datasource.username", database::getUsername)
            registry.add("spring.datasource.password", database::getPassword)
            registry.add("spring.jpa.hibernate.ddl-auto") { "update" }
        }
    }
}


# TestContainer 使用




## 1. mysql 使用

```xml

<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>testcontainers</artifactId>
    <version>1.15.2</version>
    <scope>test</scope>
</dependency>

<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <scope>test</scope>
    <version>1.15.2</version>
</dependency>

<dependency>
    <groupId>org.testcontainers</groupId>
    <!--指定数据库名称，mysql，mariadb等等-->
    <artifactId>mysql</artifactId>
    <version>1.15.2</version>
    <scope>test</scope>
</dependency>
```

### 1.1 Spring Data JPA 集成 -- springboot(高版本)

若每个测试都启动一个 `mysql` 容器:

```kotlin

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@Testcontainers
class MysqlTestContainerDemoTest {

    @Autowired
    lateinit var userRepo: UserRepo

    companion object {
        @Container
        val database = MySQLContainer<Nothing>(DockerImageName.parse("mysql").withTag("5.7.22"))

        @DynamicPropertySource
        @JvmStatic
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", database::getJdbcUrl)
            registry.add("spring.datasource.username", database::getUsername)
            registry.add("spring.datasource.password", database::getPassword)
            registry.add("spring.jpa.hibernate.ddl-auto") { "update" }
        }
    }

    @Test
    fun setup() {
        println("url : ${database.jdbcUrl}")
        println("username : ${database.username}")
        println("password : ${database.password}")
        assertNotNull(userRepo)
    }

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
```

若一次测试只启动一个 `mysql` 容器 : 
```kotlin
abstract class MysqlInit {
    companion object {
        //        @Container
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

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
//@Testcontainers
class MysqlTestContainerDemoTest : MysqlInit() {

    @Autowired
    lateinit var userRepo: UserRepo

//    companion object {
//        @Container
//        val database = MySQLContainer<Nothing>(DockerImageName.parse("mysql").withTag("5.7.22"))
//
//        @DynamicPropertySource
//        @JvmStatic
//        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
//            registry.add("spring.datasource.url", database::getJdbcUrl)
//            registry.add("spring.datasource.username", database::getUsername)
//            registry.add("spring.datasource.password", database::getPassword)
//            registry.add("spring.jpa.hibernate.ddl-auto") { "update" }
//        }
//    }


    @Test
    fun setup() {
        println("url : ${database.jdbcUrl}")
        println("username : ${database.username}")
        println("password : ${database.password}")
        assertNotNull(userRepo)
    }

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

```

### 1.2 Spring Data JPA 集成 (spring boot 低版本)

```java
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ContextConfiguration(initializers = {CategoryQueryRepoTest.Initializer.class})
@Testcontainers
class CategoryQueryRepoTest {

    @Autowired
    private CategoryQueryRepo repo;

    @Container
    private static final MySQLContainer<?> db = new MySQLContainer<>(DockerImageName.parse("mysql").withTag("5.7.22"));

    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext context) {
            TestPropertyValues.of(
                    "spring.datasource.url=" + db.getJdbcUrl(),
                    "spring.datasource.username=" + db.getUsername(),
                    "spring.datasource.password=" + db.getPassword(),
                    "spring.jpa.hibernate.ddl-auto=update"
            ).applyTo(context.getEnvironment());
        }
    }

    @Test
    void findAtTheWarehouseAll() {
        List<AtTheWarehouse> list = repo.findAtTheWarehouseAll("a", Arrays.asList("1"));
        assertEquals(0, list.size());
    }

}


```

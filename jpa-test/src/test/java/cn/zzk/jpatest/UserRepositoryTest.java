package cn.zzk.jpatest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.persistence.Tuple;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void nativeFindAll() {
        Location location = Location.createCabinetLocation("1", "1");
        location = entityManager.persist(location);
        User user = new User().setName("张三").setLocation(location);
        entityManager.persist(user);


        List<User> users = userRepository.nativeFindAll();
        assertEquals(1, users.size());

        //因为拿到是实体(或者说是被 hibernate 代理的实体), EntityManager 会偷偷的查询 Location
        assertNotNull(users.get(0).getLocation());


        List<User> list = jdbcTemplate.query("select * from user", (rs, index) -> {
            User user1 = new User();
            user1.setId(rs.getString("id"));
            user1.setName(rs.getString("name"));
            return user1;
        });
        assertEquals(1, list.size());
        assertNull(list.get(0).getLocation());

        //因为拿到是实体(或者说是被 hibernate 代理的实体), EntityManager 会偷偷的查询 Location
        List<User> resultList = entityManager
                .getEntityManager()
                .createNativeQuery("select * from user ", User.class)
                .getResultList();
        assertEquals(1, resultList.size());
        assertNotNull(resultList.get(0).getLocation());


        List<Tuple> resultList1 = entityManager
                .getEntityManager()
                .createNativeQuery("select * from user", Tuple.class)
                .getResultList();

        List<User> collect = resultList1
                .stream().map(tuple -> {
                    User user1 = new User();
                    user1.setId(tuple.get("id", String.class));
                    user1.setName(tuple.get("name", String.class));
                    return user1;
                }).collect(Collectors.toList());
        assertEquals(1, collect.size());
        assertNull(collect.get(0).getLocation());

    }
}
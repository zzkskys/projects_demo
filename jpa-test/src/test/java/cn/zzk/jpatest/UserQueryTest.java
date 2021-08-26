package cn.zzk.jpatest;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created Date : 2021/08/26
 *
 * @author zzk
 */
@DataJpaTest
public class UserQueryTest {

    @Autowired
    private NamedParameterJdbcTemplate template;

    @Sql(statements = {
            "insert into user(id,name,create_time) values ('1','张三',0)",
            "insert into user(id,name,create_time) values ('2',null,1)"
    }

    )
    @Test
    void query() {
        String sql = "select * from user where name = :name and create_time = :createTime";

        UserQuery query = new UserQuery()
                .setName("张三")
                .setId("1")
                .setCreateTime(0);
        BeanPropertySqlParameterSource source = new BeanPropertySqlParameterSource(query);
        List<UserDTO> list = template.query(sql, source, new BeanPropertyRowMapper<>(UserDTO.class));
        assertEquals(1, list.size());

    }

    @Accessors(chain = true)
    @Data
    public static class UserQuery {
        private String id;
        private String name;
        private long createTime;
    }

    @Data
    public static class UserDTO {
        private String id;
        private String name;
    }
}

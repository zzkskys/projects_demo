package com.example.mybatistest;

import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(PageHelperTestConfig.class)
@MybatisTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;


    @Sql(statements = {
            "insert into user(id, name, create_time) values ('1', '张三', '2021-06-21')",
            "insert into user(id, name, create_time) values ('2', '李四', '2021-06-23')",
    })
    @Test
    void findAll() {
        assertNotNull(userRepo);
        List<User> all = userRepo.findAll(LocalDateTime.MIN, LocalDateTime.MAX);
        assertEquals(2, all.size());
    }

    @Sql(statements = {
            "insert into user(id, name, create_time) values ('1', '张三', '2021-06-21')",
            "insert into user(id, name, create_time) values ('2', '李四', '2021-06-23')",
    })
    @Test
    void findUsers() {
        assertNotNull(userRepo);
        UserQuery userQuery = new UserQuery().setEndTime(LocalDateTime.of(2021, 6, 22, 0, 0));
        List<User> all = userRepo.findUsers(userQuery);
        assertEquals(1, all.size());
    }


    @Sql(statements = {
            "insert into user(id, name, create_time) values ('1', '张三', '2021-06-21')",
            "insert into user(id, name, create_time) values ('2', '李四', '2021-06-23')",
    })
    @Test
    void findPage() {
        UserQuery userQuery = new UserQuery();
        PageInfo<User> page = userRepo.findPage(userQuery, PageRequest.of(0, 1));
        assertEquals(2, page.getTotal());
        assertEquals(1, page.getList().size());
    }
}
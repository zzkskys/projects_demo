package com.example.mybatisplus.domain;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.example.mybatisplus.config.MybatisPlusConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(MybatisPlusConfig.class)
@MybatisPlusTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    void findAll() {
        assertNotNull(userRepository);
        List<User> users = userRepository.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void insert() {
        User user = new User()
                .setAge(18)
                .setName("张三")
                .setEmail("233@qq.com");
        userRepository.insert(user);
        long id = user.getId();

        User update = new User()
                .setAge(19);
        update.setId(id);
        userRepository.updateById(update);

        assertEquals(1, userRepository.selectById(id).getVersion());
    }

    @Test
    void update() {
        assertEquals(0, userRepository.selectById(1).getVersion());

        User user = new User()
                .setName("张三");
        user.setId(1L);
        assertEquals(1, userRepository.updateById(user));
        assertEquals(1, userRepository.selectById(1).getVersion());

    }
}
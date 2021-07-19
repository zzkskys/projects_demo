package com.example.mybatisplus.domain;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * lambda  表达式查询,推荐单表查询使用
     */
    @Test
    void query1() {
        LambdaQueryWrapper<User> jone = new LambdaQueryWrapper<User>()
                .eq(User::getName, "Jone")
                .and(user -> user.eq(User::getAge, 18));
        List<User> users = userRepository.selectList(jone);
        assertEquals(1, users.size());
    }

    @Test
    void findAllPage() {
        IPage<User> page = userRepository.findAll(new Page<>(0, 1));
        assertEquals(1, page.getRecords().size());
        assertEquals(5, page.getTotal());
    }

    @Test
    void findDeleted() {
        userRepository.deleteById(1);
        List<User> users = userRepository.selectList(null);
        assertEquals(4, users.size());

        List<User> deleted = userRepository.findDeleted();
        assertEquals(1,deleted.size());
    }
}
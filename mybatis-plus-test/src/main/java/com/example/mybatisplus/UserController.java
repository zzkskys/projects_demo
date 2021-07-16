package com.example.mybatisplus;

import com.example.mybatisplus.domain.User;
import com.example.mybatisplus.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/add")
    public void add(String name, int age) {
        User user = new User()
                .setName(name)
                .setAge(age);
        userRepository.insert(user);
    }

    @GetMapping("/update")
    public void update(long id, int age) {
        User user = userRepository.selectById(id);
        user.setAge(age);
        userRepository.updateById(user);
    }

    @GetMapping
    public List<User> findAll() {
        return userRepository.selectList(null);
    }
}

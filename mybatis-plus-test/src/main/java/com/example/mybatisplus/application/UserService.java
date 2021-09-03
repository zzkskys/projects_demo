package com.example.mybatisplus.application;

import com.example.mybatisplus.domain.User;
import com.example.mybatisplus.domain.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * Created Date : 2021/09/01
 *
 * @author zzk
 */
@Service
@AllArgsConstructor
@Validated
public class UserService {

    private final UserRepository userRepository;


    public void addUser(@Valid AddUserCommand command) {
        if (userRepository.existsByName(command.getName())) {
            throw new RuntimeException("用户名称已存在");
        }
        User user = new User()
                .setName(command.getName())
                .setRole(command.getRole());
        userRepository.insert(user);
    }
}

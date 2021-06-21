package com.example.mybatistest;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@AllArgsConstructor
public class UserController {

    private final UserRepo userRepo;

    @GetMapping("/users")
    public PageInfo<User> findAll(int page, int size) {
        return userRepo.findPage(new UserQuery(), PageRequest.of(page, size));
    }
}

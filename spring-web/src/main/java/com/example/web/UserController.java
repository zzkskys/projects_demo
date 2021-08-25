package com.example.web;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Validated
public class UserController {

    private final WebService service;

    @GetMapping
    public void addUser(String name,
                        int age) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        service.addUser(user);
    }
}

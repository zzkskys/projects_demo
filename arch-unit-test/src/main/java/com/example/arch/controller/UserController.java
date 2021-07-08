package com.example.arch.controller;

import com.example.arch.application.UserAppService;
import com.example.arch.infrastructure.query.UserQueryRepo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserAppService userAppService;

    private final UserQueryRepo userQueryRepo;
}

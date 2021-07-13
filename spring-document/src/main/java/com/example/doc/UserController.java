package com.example.doc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Api(tags = "用户接口")
public class UserController {

    @ApiOperation(value = "添加用户", notes = "添加用户接口")
    @PostMapping
    public User addUser(@RequestBody @ApiParam(value = "添加用户对象", required = true) User user) {
        return user;
    }

    @ApiOperation("根据 ID 查找用户")
    @GetMapping("/{id}")
    public User findById(@PathVariable String id) {
        return new User();
    }
}

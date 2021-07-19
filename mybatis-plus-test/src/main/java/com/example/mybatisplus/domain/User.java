package com.example.mybatisplus.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(chain = true)
public class User extends AbstractEntity {


    private String name;

    private int age;

    private String email;

    private Role role = Role.USER;
}

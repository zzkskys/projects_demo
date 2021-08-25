package com.example.web;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class User {

    @NotNull(message = "姓名不能为null")
    private String name;

    @Min(value = 18, message = "年龄必须不小于18")
    private int age;
}

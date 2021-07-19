package com.example.mybatisplus.domain;

import com.baomidou.mybatisplus.annotation.IEnum;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role implements IEnum<Integer> {

    USER(1, "普通用户"),
    ADMIN(2, "管理员");


    private final int value;
    private final String desc;


    @Override
    public Integer getValue() {
        return value;
    }
}

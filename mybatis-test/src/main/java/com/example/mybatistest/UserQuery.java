package com.example.mybatistest;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class UserQuery {

    private LocalDateTime startTime = LocalDateTime.MIN;

    private LocalDateTime endTime = LocalDateTime.MAX;

}

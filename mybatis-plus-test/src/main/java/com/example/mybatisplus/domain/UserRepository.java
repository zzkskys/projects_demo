package com.example.mybatisplus.domain;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseMapper<User> {
}

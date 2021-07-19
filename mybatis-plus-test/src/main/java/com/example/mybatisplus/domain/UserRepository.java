package com.example.mybatisplus.domain;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository extends BaseMapper<User> {

    IPage<User> findAll(Page<User> page);

    List<User> findDeleted();
}

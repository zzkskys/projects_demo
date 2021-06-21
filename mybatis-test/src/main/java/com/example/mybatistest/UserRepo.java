package com.example.mybatistest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserRepo {

    List<User> findAll(@Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    List<User> findUsers(UserQuery userQuery);

    default PageInfo<User> findPage(UserQuery userQuery, Pageable pageable) {
        return PageHelper
                .startPage(pageable.getPageNumber(), pageable.getPageSize())
                .doSelectPageInfo(() -> findUsers(userQuery));
//        List<User> users = findUsers(userQuery);
//        return new PageInfo<>(users);
    }

}

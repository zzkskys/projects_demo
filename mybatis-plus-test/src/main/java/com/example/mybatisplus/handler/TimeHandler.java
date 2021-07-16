package com.example.mybatisplus.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeHandler implements MetaObjectHandler {

    public static final String CREATE_TIME_FIELD = "createTime";
    public static final String UPDATE_TIME_FIELD = "updateTime";

    @Override
    public void insertFill(MetaObject metaObject) {
        LocalDateTime now = LocalDateTime.now();
        this.strictInsertFill(metaObject, CREATE_TIME_FIELD, LocalDateTime.class, now);
        this.strictInsertFill(metaObject, UPDATE_TIME_FIELD, LocalDateTime.class, now);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, UPDATE_TIME_FIELD, LocalDateTime.class, LocalDateTime.now());
    }
}

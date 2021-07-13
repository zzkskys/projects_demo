package com.example.doc.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 只有超级管理员才可操作的API。
 * 部分 API 具有一定的危险性，因此设置只有超级管理员才可操作。
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SuperAdminAPI {
}

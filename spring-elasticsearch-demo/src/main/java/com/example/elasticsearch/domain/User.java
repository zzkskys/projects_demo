package com.example.elasticsearch.domain;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created Date : 2021/08/31
 *
 * @author zzk
 */
@Data
@Accessors(chain = true)
public class User {
    private String name;

    private int age;
}

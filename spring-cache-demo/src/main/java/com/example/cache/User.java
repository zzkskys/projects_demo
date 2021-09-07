package com.example.cache;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created Date : 2021/09/07
 *
 * @author zzk
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@Entity
public class User {

    @Id
    private String id = UUID.randomUUID().toString();

    private String name;

    private int age;
}

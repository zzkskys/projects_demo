package cn.zzk.cucumber;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class User {
    private String id = UUID.randomUUID().toString();

    private String name;

    private String password;
}

package cn.zzk.mapstruct;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Student {

    private String name;

    private int age;

    private String number;
}

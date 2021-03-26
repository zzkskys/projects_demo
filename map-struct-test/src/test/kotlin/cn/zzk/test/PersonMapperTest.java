package cn.zzk.test;

import cn.zzk.mapstruct.Person;
import cn.zzk.mapstruct.Student;
import cn.zzk.mapstruct.StudentMapper;
import cn.zzk.mapstruct.StudentNumber;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonMapperTest {

    @Test
    void test() {
        StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
        Person person = new Person().setAge(18).setName("张三");
        StudentNumber number = new StudentNumber().setNumber("aaa");

        Student student = mapper.toStudent(person, number);
        assertThat(student)
                .hasFieldOrPropertyWithValue("name", "张三")
                .hasFieldOrPropertyWithValue("age", 18)
                .hasFieldOrPropertyWithValue("number", "aaa");

    }

    @Test
    void updateStudent() {

    }
}

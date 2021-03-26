package cn.zzk.mapstruct;

import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {

    Person toPerson(Student student);

    Student toStudent(Person person, StudentNumber number);
}

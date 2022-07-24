package com.hanhui.stumanage.mapper;


import com.hanhui.stumanage.entity.StudentEntity;
import com.hanhui.stumanage.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    Student fromEntity(StudentEntity entity);

    List<Student> fromEntities(List<StudentEntity> entities);

    StudentEntity fromModel(Student model);
}

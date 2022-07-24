package com.hanhui.stumanage.mapper;


import com.hanhui.stumanage.entity.TeacherEntity;
import com.hanhui.stumanage.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);


    TeacherEntity fromModel(Teacher model);

    Teacher fromEntity(TeacherEntity entity);

    List<Teacher> fromEntities(List<TeacherEntity> entities);
}

package com.hanhui.stumanage.mapper;


import com.hanhui.stumanage.entity.CourseEntity;
import com.hanhui.stumanage.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    CourseEntity fromModel(Course course);

    Course fromEntity(CourseEntity entity);

    List<Course> fromEntities(List<CourseEntity> entities);
}

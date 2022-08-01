package com.hanhui.stumanage.mapper;


import com.hanhui.stumanage.entity.CourseStuentRelEntity;
import com.hanhui.stumanage.model.CourseStudent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CourseStudenMapper {

    CourseStudenMapper INSTANCE = Mappers.getMapper(CourseStudenMapper.class);


    CourseStudent fromEntity(CourseStuentRelEntity entity);

    List<CourseStudent> fromEntities(List<CourseStuentRelEntity> entities);
}

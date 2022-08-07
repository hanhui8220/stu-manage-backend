package com.hanhui.stumanage.mapper;


import com.hanhui.stumanage.entity.ScoreStudentEntity;
import com.hanhui.stumanage.model.ScoreStudent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ScoreStudentMapper {

    ScoreStudentMapper INSTANCE = Mappers.getMapper(ScoreStudentMapper.class);

    ScoreStudentEntity fromModel(ScoreStudent scoreStudent);

    ScoreStudent fromEntity(ScoreStudentEntity entity);

    List<ScoreStudent> fromEntities(List<ScoreStudentEntity> entities);
}

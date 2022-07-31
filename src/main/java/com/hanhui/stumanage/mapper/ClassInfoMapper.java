package com.hanhui.stumanage.mapper;

import com.hanhui.stumanage.entity.ClassEntity;
import com.hanhui.stumanage.model.ClassInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface ClassInfoMapper {

    ClassInfoMapper INSTANCE = Mappers.getMapper(ClassInfoMapper.class);

    ClassEntity fromModel(ClassInfo model);

    ClassInfo fromEntity(ClassEntity entity);

    List<ClassInfo> fromEntities(List<ClassEntity> entities);
}

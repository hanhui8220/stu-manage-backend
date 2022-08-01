package com.hanhui.stumanage.mapper;


import com.hanhui.stumanage.entity.MajorEntity;
import com.hanhui.stumanage.model.Major;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MajorMapper {

    MajorMapper INSTANCE = Mappers.getMapper(MajorMapper.class);

    Major fromEntity(MajorEntity entity);

    List<Major> fromEntities(List<MajorEntity> entities);

    MajorEntity fromModel(Major model);
}

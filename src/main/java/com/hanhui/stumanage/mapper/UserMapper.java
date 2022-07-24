package com.hanhui.stumanage.mapper;


import com.hanhui.stumanage.entity.UserEntity;
import com.hanhui.stumanage.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User fromEntity(UserEntity entity);

    List<User> fromEntities(List<UserEntity> entities);

    UserEntity fromModel(User model);
}

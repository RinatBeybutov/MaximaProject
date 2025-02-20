package com.maxima.userService.mapper;

import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserViewDto;
import com.maxima.userService.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

/**
 * Интерфейс преобразования между сущностями и DTO пользователей.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

  UserEntity toEntity(UserCreateDto userCreateDto);

  UserViewDto toDto(UserEntity userEntity);

  void update(UserCreateDto dto, @MappingTarget UserEntity user);
}

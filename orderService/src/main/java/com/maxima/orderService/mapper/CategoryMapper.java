package com.maxima.orderService.mapper;

import com.maxima.orderService.dto.CategoryDto;
import com.maxima.orderService.entity.CategoryEntity;
import com.maxima.orderService.dto.CategoryCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Интерфейс для преобразования между сущностями и DTO Категорий.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper {

  CategoryDto toDto(CategoryEntity categoryEntity);

  CategoryEntity toEntity(CategoryCreateDto dto);

  void update(CategoryCreateDto categoryInputDto, @MappingTarget CategoryEntity categoryEntity);
}

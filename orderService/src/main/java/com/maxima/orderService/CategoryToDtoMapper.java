package com.maxima.orderService;

import com.maxima.orderService.dto.CategoryDto;
import com.maxima.orderService.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryToDtoMapper {
    CategoryEntity dtoToEntity(CategoryDto categoryDto);
    CategoryDto entityToDto(CategoryEntity categoryEntity);
}

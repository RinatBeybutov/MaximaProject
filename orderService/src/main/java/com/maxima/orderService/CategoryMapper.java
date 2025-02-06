package com.maxima.orderService;

import com.maxima.orderService.dto.CategoryDto;
import com.maxima.orderService.entity.CategoryEntity;
import com.maxima.orderService.dto.CategoryCreateDto;

import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapper;
 

@Mapper(componentModel = "spring")
public abstract class CategoryMapper {
    public abstract CategoryEntity dtoToEntity(CategoryDto categoryDto);
    public abstract CategoryDto entityToDto(CategoryEntity categoryEntity);

    public abstract CategoryEntity dtoToEntity(CategoryCreateDto dto);

    @AfterMapping
    protected void setUuid(@MappingTarget CategoryEntity entity) {
        entity.setUuid(java.util.UUID.randomUUID());
    }
}

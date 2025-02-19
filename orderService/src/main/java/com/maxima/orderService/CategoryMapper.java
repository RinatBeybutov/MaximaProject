package com.maxima.orderService;

import com.maxima.orderService.dto.CategoryDto;
import com.maxima.orderService.entity.CategoryEntity;
import com.maxima.orderService.dto.CategoryCreateDto;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(CategoryEntity categoryEntity);

    CategoryEntity toEntity(CategoryCreateDto dto);

    void update(CategoryCreateDto categoryInputDto, @MappingTarget CategoryEntity categoryEntity);

    @AfterMapping
    default void setUuid(@MappingTarget CategoryEntity entity) {
        if(entity.getUuid()==null) entity.setUuid(java.util.UUID.randomUUID());
    }
}

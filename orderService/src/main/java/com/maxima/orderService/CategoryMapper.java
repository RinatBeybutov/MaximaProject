package com.maxima.orderService;

import com.maxima.orderService.dto.CategoryDto;
import com.maxima.orderService.entity.CategoryEntity;
import com.maxima.orderService.dto.CategoryCreateDto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(CategoryEntity categoryEntity);

    CategoryEntity toEntity(CategoryCreateDto dto);

    void updateFromDto(CategoryCreateDto categoryInputDto, @MappingTarget CategoryEntity categoryEntity);


}

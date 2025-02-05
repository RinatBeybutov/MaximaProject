package com.maxima.orderService;

import com.maxima.orderService.dto.CategoryCreateDto;
import com.maxima.orderService.entity.CategoryEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class CreateDtoToCategoryMapper {
    public abstract CategoryEntity dtoToEntity(CategoryCreateDto dto);

    @AfterMapping
    protected void setUuid(@MappingTarget CategoryEntity entity) {
        entity.setUuid(java.util.UUID.randomUUID());
    }
}

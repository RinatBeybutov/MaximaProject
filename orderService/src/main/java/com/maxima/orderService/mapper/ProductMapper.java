package com.maxima.orderService.mapper;

import com.maxima.orderService.dto.ProductViewDto;
import com.maxima.orderService.dto.ProductCreateDto;
import com.maxima.orderService.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

/**
 * Интерфейс для преобразования между сущностями и DTO Категорий.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

  @Mapping(source = "category.uuid", target = "categoryUuid")
  ProductViewDto toDto(ProductEntity productEntity);

  ProductEntity toEntity(ProductCreateDto dto);

  void update(ProductCreateDto productInputDto, @MappingTarget ProductEntity productEntity);
}

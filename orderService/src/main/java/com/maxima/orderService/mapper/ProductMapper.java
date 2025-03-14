package com.maxima.orderService.mapper;

import com.maxima.orderService.dto.ProductViewDto;
import com.maxima.orderService.dto.ProductCreateDto;
import com.maxima.orderService.dto.ProductWithCountDto;
import com.maxima.orderService.entity.ProductEntity;
import com.maxima.orderService.entity.ProductToOrderEntity;
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

  @Mapping(target = "name", source = "product.name")
  @Mapping(target = "categoryUuid", source = "product.category.uuid")
  @Mapping(target = "uuid", source = "product.uuid")
  ProductWithCountDto toCountDto(ProductToOrderEntity productToOrderEntity);
}

package com.maxima.orderService;

import com.maxima.orderService.dto.ProductToOrderDto;
import com.maxima.orderService.entity.OrderEntity;
import com.maxima.orderService.entity.ProductToOrderEntity;
import java.time.LocalDateTime;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Интерфейс для преобразования между сущностями и DTO таблицы связи Продукт - Заказ
 */
@Mapper(componentModel = "spring")
public interface ProductToOrderMapper {
  ProductToOrderEntity toEntity(ProductToOrderDto dto);

}

package com.maxima.orderService;

import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderDto;
import com.maxima.orderService.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Интерфейс для преобразования между сущностями и DTO Заказов.
 */

@Mapper(componentModel = "spring")
public interface OrderMapper {

  OrderDto toDto(OrderEntity orderEntity);

  OrderEntity toEntity(OrderCreateDto dto);

  void update(OrderCreateDto orderInputDto, @MappingTarget OrderEntity orderEntity);
}

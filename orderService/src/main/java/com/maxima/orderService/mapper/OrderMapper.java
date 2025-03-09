package com.maxima.orderService.mapper;

import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderDto;
import com.maxima.orderService.dto.OrderUpdateDto;
import com.maxima.orderService.dto.OrderViewDto;
import com.maxima.orderService.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Интерфейс для преобразования между сущностями и DTO Заказов.
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {

  OrderDto toDto(OrderEntity orderEntity);

  OrderViewDto toViewDto(OrderEntity orderEntity);

  OrderEntity toEntity(OrderCreateDto dto);

  void update(OrderUpdateDto dto, @MappingTarget OrderEntity orderEntity);
}
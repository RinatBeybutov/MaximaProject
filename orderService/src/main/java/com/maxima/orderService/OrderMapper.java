package com.maxima.orderService;

import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderDto;
import com.maxima.orderService.dto.OrderUpdateDto;
import com.maxima.orderService.entity.OrderEntity;
import com.maxima.orderService.entity.OrderStatus;
import java.time.LocalDateTime;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

/**
 * Интерфейс для преобразования между сущностями и DTO Заказов.
 */

@Mapper(componentModel = "spring")
public interface OrderMapper {

  OrderDto toDto(OrderEntity orderEntity);

  OrderEntity toEntity(OrderCreateDto dto);

  void update(OrderUpdateDto dto, @MappingTarget OrderEntity orderEntity);

  @BeforeMapping
  default void fillFields(@MappingTarget OrderEntity orderEntity) {
    if (orderEntity.getStatus() == null) {
      orderEntity.setStatus(OrderStatus.CREATED);
      orderEntity.setCreatedAt(LocalDateTime.now());
    }
  }
}

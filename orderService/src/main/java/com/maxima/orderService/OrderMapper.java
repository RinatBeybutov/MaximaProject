package com.maxima.orderService;

import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderDto;
import com.maxima.orderService.dto.OrderUpdateDto;
import com.maxima.orderService.dto.OrderViewDto;
import com.maxima.orderService.entity.OrderEntity;
import com.maxima.orderService.entity.ProductToOrderEntity;
import com.maxima.orderService.repository.OrderRepository;
import com.maxima.orderService.repository.ProductToOrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Интерфейс для преобразования между сущностями и DTO Заказов.
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class OrderMapper {

  @Autowired
  private ProductToOrderRepository repository;

  @Autowired
  private OrderRepository orderRepository;

  public abstract OrderDto toDto(OrderEntity orderEntity);

  public abstract OrderViewDto toViewDto(OrderEntity orderEntity);

  public abstract OrderEntity toEntity(OrderCreateDto dto);

  public abstract void update(OrderUpdateDto dto, @MappingTarget OrderEntity orderEntity);

  /**
   * Метод для заполнения поля products в дто заказов
   */
  @AfterMapping
  public void fillFields1(@MappingTarget OrderViewDto dto) {
    List<ProductToOrderEntity> v = repository.findAllByOrderId(
        orderRepository.getByUuid(dto.getUuid()).getId());
    var v1 = v.stream()
        .map(e -> e.getOrder().getUuid())
        .collect(Collectors.toList());
    dto.setProducts(v1);
  }
}
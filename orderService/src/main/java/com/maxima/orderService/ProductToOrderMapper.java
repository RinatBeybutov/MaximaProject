package com.maxima.orderService;

import com.maxima.orderService.dto.ProductToOrderDto;
import com.maxima.orderService.entity.ProductToOrderEntity;
import com.maxima.orderService.repository.OrderRepository;
import com.maxima.orderService.repository.ProductRepository;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Интерфейс для преобразования между сущностями и DTO таблицы связи Продукт - Заказ
 */
@Mapper(componentModel = "spring")
public abstract class ProductToOrderMapper {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ProductRepository productRepository;

  public abstract ProductToOrderEntity toEntity(ProductToOrderDto dto);

  @AfterMapping
  public void fillFields(@MappingTarget ProductToOrderEntity entity) {
    System.out.println(entity.getOrderId());
    System.out.println(orderRepository.findById(entity.getOrderId()).get().getId());
    entity.setOrder(orderRepository.findById(entity.getOrderId()).get());
    entity.setProduct(productRepository.findById(entity.getProductId()).get());
  }

}

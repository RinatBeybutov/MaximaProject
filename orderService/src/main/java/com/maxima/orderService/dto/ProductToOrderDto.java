package com.maxima.orderService.dto;

import com.maxima.orderService.entity.OrderEntity;
import com.maxima.orderService.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductToOrderDto {
  private ProductEntity product;

  private OrderEntity order;

  private Long count;
}

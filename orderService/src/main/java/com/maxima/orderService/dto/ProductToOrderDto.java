package com.maxima.orderService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО для класса ProductToOrderEntity
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductToOrderDto {
  private Long productId;

  private Long orderId;

  private Long count;
}

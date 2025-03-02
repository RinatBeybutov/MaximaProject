package com.maxima.orderService.dto;

import com.maxima.orderService.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО для создания продукта.
 */
@Schema(description = "ДТО для создания продукта")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderUpdateDto {
  @Schema(description = "Статус заказа", example = "CREATED")
  private OrderStatus status;

}

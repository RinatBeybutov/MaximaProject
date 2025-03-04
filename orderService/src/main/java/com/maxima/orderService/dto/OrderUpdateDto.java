package com.maxima.orderService.dto;

import com.maxima.orderService.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО для изменения статуса продукта в БД
 */
@Schema(description = "ДТО для изменения статуса продукта в БД")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderUpdateDto {

  @Schema(description = "Статус заказа", example = "CREATED")
  private OrderStatus status;

}

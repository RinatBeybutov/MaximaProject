package com.maxima.orderService.dto;

import com.maxima.orderService.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Дто сущности заказа со списком товаров
 */
@Schema(description = "Дто сущности заказа со списком товаров")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderViewDto {

  @Schema(description = "Глобальный идентификатор заказа", example = "acc49792-9c0b-49f7-9fce-5d9d631d045f")
  private UUID uuid;

  private LocalDateTime createdAt;

  private UUID userUuid;

  private OrderStatus status;

  private List<UUID> products;
}

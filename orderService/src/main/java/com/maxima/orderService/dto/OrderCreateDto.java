package com.maxima.orderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Map;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Дто сущности заказа для ввода с фронтэнда
 */
@Schema(description = "Дто сущности заказа для ввода с фронтэнда")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {
  private UUID userUuid;

  private Map<UUID,Long> productsCost;

}

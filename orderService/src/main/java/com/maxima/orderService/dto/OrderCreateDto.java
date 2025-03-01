package com.maxima.orderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Дто сущности заказа для ввода с фронтэнда")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreateDto {

  private LocalDateTime createdAt;

  private UUID userUuid;

  private Integer status;

}

package com.maxima.orderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Дто сущности заказа")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

  @Schema(description = "Идентификатор заказа", example = "1")
  private Long id;

  @Schema(description = "Глобальный идентификатор заказа", example = "fcc49792-9c0b-49f7-9fce-5d9d631d045f")
  private UUID uuid;

  private LocalDateTime createdAt;

  private UUID userUuid;

  private Integer status;

}

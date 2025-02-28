package com.maxima.orderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО сущности продукта.
 */
@Schema(description = "ДТО сущности продукта")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductViewDto {

  @Schema(description = "Индитификатор продукта", example = "1")
  private Long id;

  @Schema(description = "Глобальный индитификатор продукта", example = "1867e5bc-3b07-45f8-b2a6-be1e01274adc")
  private UUID uuid;

  @Schema(description = "Идентификатор категории, к которой относится продукт")
  private Integer categoryId;

  @Schema(description = "Название продукта", example = "Сыр")
  private String name;

}

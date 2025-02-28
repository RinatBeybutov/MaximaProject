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

  @Schema(description = "Глобальный индитификатор продукта", example = "1867e5bc-3b07-45f8-b2a6-be1e01274adc")
  private UUID uuid;

  @Schema(description = "Глобальный идентификатор категории, к которой относится продукт", example = "05e34db4-b626-4bbc-ab47-e32e2dbab680")
  private UUID categoryUuid;

  @Schema(description = "Название продукта", example = "Сыр")
  private String name;

}

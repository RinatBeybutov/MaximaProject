package com.maxima.orderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
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
public class ProductCreateDto {

  @Schema(description = "Название продукта", example = "Томаты")
  private String name;

  @Schema(description = "Глобальный идентификатор категории, к которой относится продукт", example = "05e34db4-b626-4bbc-ab47-e32e2dbab680")
  private UUID categoryUuid;
}

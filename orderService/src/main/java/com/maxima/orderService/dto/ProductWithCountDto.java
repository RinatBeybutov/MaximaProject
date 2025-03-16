package com.maxima.orderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс для DTO для заказа с количеством товаров.
 */
@Schema(description = "Дто сущности продукта с его количеством")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductWithCountDto {

  @Schema(description = "Количество продукта", example = "4")
  private Long count;

  @Schema(description = "Глобальный индитификатор продукта", example = "1867e5bc-3b07-45f8-b2a6-be1e01274adc")
  private UUID uuid;

  @Schema(description = "Глобальный идентификатор категории, к которой относится продукт", example = "05e34db4-b626-4bbc-ab47-e32e2dbab680")
  private UUID categoryUuid;

  @Schema(description = "Название продукта", example = "Сыр")
  private String name;
}

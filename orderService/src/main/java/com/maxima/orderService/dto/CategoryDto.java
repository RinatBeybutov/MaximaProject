package com.maxima.orderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.UUID;

/**
 * Дто сущности категории
 */
@Schema(description = "Дто сущности категории")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

  @Schema(description = "Идентификатор категории", example = "1")
  private Long id;

  @Schema(description = "Глобальный идентификатор категории", example = "fcc49792-9c0b-49f7-9fce-5d9d631d045f")
  private UUID uuid;

  @Schema(description = "Название категории", example = "Молочные продукты")
  private String name;

}

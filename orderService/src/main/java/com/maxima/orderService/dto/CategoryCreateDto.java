package com.maxima.orderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Дто сущности категории для ввода с фронтэнда
 */
@Schema(description = "Дто сущности категории для ввода с фронтэнда")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDto {

  @Schema(description = "Название категории")
  private String name;
}

package com.maxima.orderService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.UUID;

/**
* Дто сущности категории для ввода с фронтэнда
*/
@Schema(description = "Дто сущности категории для ввода с фронтэнда")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDto {
    @Schema(description = "Название категории")
    private String name;
}

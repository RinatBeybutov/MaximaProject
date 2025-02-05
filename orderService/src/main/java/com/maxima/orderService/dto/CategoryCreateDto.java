package com.maxima.orderService.dto;

import lombok.*;
import java.util.UUID;

/**
* Дто сущности категории для ввода с фронтэнда
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateDto {
    private String name;
}

package com.maxima.orderService.dto;

import lombok.*;
import java.util.UUID;

/**
* Дто сущности категории для ввода с фронтэнда
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryInputDto {
    private UUID uuid;
    private String name;
}

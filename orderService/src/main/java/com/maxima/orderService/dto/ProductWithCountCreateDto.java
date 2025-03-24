package com.maxima.orderService.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;
import lombok.ToString;

/**
 * Класс для DTO товаров с количеством для создания заказа.
 */
@Getter
@Setter
@Builder
@ToString
public class ProductWithCountCreateDto {

  private UUID uuid;

  private Long count;
}

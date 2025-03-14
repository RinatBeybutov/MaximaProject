package com.maxima.orderService.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс для DTO для заказа с количеством товаров.
 */
@Getter
@Setter
public class ProductWithCountDto extends ProductViewDto {

  private Long count;

}

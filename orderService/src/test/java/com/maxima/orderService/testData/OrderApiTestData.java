package com.maxima.orderService.testData;

import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderViewDto;
import com.maxima.orderService.dto.ProductWithCountCreateDto;
import java.util.List;
import java.util.UUID;

/**
 * Класс для тестовых данных
 */
public class OrderApiTestData {

  public static final UUID ORDER_UUID = UUID.fromString("423bd97c-f1af-413c-9f62-18b4ab158293");

  /**
   * Создание OrderCreateDto
   */
  public static OrderCreateDto orderCreateDto() {
    return OrderCreateDto.builder()
        .userUuid(UUID.fromString("423bd97c-f1af-413c-9f62-18b4ab158293"))
        .products(List.of(
            ProductWithCountCreateDto.builder()
                .uuid(UUID.fromString("550e8400-e29b-41d4-a716-446655440000"))
                .count(2L)
                .build()
        ))
        .build();
  }

  public static OrderViewDto getViewOrderDto() {
    return OrderViewDto.builder()
        .uuid(ORDER_UUID)
        .userUuid(UUID.fromString("423bd97c-f1af-413c-9f62-18b4ab158293"))
        .build();
  }
}
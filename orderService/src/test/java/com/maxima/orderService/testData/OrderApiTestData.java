package com.maxima.orderService.testData;

import com.maxima.orderService.dto.OrderViewDto;
import com.maxima.orderService.dto.ProductWithCountDto;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Класс с тестовыми данными для OrderApi
 */
public class OrderApiTestData {

  public static final UUID ORDER_UUID = UUID.fromString("63a31acf-5450-42a0-8f9b-5d732bbdf397");

  public static final int ORDER_NUMBER = 4;

  public static final int ORDER_STATUS = 2;

  public static OrderViewDto getViewOrderDto() {
    OrderViewDto orderViewDtoDto = new OrderViewDto();
    orderViewDtoDto.setUuid(UUID.fromString("36c4caec-48ba-4099-abc6-cf80026905d6"));
    orderViewDtoDto.setCreatedAt(LocalDateTime.of(2025, 1, 25, 23, 59, 59));
    orderViewDtoDto.setUserUuid(UUID.fromString("423bd97c-f1af-413c-9f62-18b4ab158293"));
    List<ProductWithCountDto> products = List.of(
        getProductWithCountDto1(),
        getProductWithCountDto2()
    );
    orderViewDtoDto.setProducts(products);
    return orderViewDtoDto;
  }

  private static ProductWithCountDto getProductWithCountDto2() {
    ProductWithCountDto dto = new ProductWithCountDto();
    dto.setUuid(UUID.fromString("77ff09f2-28cd-4ec0-865b-2e4917433631"));
    dto.setCount(3L);
    dto.setCategoryUuid(UUID.fromString("63a31acf-5450-42a0-8f9b-5d732bbdf397"));
    dto.setName("Вода");
    return dto;
  }

  private static ProductWithCountDto getProductWithCountDto1() {
    ProductWithCountDto dto = new ProductWithCountDto();
    dto.setUuid(UUID.fromString("c4ec28c3-2369-4709-9b44-355d3e2d5640"));
    dto.setCount(2L);
    dto.setCategoryUuid(UUID.fromString("c4ec28c3-2369-4709-9b44-355d3e2d5640"));
    dto.setName("Coca-cola");
    return dto;
  }
}

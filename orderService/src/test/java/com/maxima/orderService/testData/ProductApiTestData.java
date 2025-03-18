package com.maxima.orderService.testData;

import com.maxima.orderService.dto.ProductCreateDto;
import com.maxima.orderService.dto.ProductViewDto;
import java.util.UUID;

/**
 * Класс с данными для тестов
 */
public class ProductApiTestData {

  public static final UUID WRONG_UUID = UUID.fromString("974df0be-8fe6-4cb8-8e71-b307567c3e60");

  public static final int NUMBER_OF_PRODUCTS = 3;

  public static ProductCreateDto productCreateDto() {
    return ProductCreateDto.builder()
        .name("test category")
        .categoryUuid(UUID.fromString("fcc49792-9c0b-49f7-9fce-5d9d631d045f"))
        .build();
  }

  public static ProductCreateDto productUpda teDto() {
    return ProductCreateDto.builder()
        .name("new name")
        .categoryUuid(UUID.fromString("fcc49792-9c0b-49f7-9fce-5d9d631d042f"))
        .build();
  }

  public static ProductViewDto createdViewDto() {
    return ProductViewDto.builder()
        .categoryUuid(UUID.fromString("fcc49792-9c0b-49f7-9fce-5d9d631d045f"))
        .name("test category")
        .build();
  }

  public static ProductViewDto updatedProductDto() {
    return ProductViewDto.builder()
        .name("new name")
        .categoryUuid(UUID.fromString("fcc49792-9c0b-49f7-9fce-5d9d631d042f"))
        .build();
  }

  /**
   * Создает экземпляр ProductViewDto с тестовыми данными для продукта Coca-Cola
   *
   * @return ProductViewDto с предустановленными значениями name, categoryUuid и uuid
   */
  public static ProductViewDto colaProductDto() {
    return ProductViewDto.builder()
        .name("Coca-Cola")
        .categoryUuid(UUID.fromString("fcc49792-9c0b-49f7-9fce-5d9d631d045f"))
        .uuid(UUID.fromString("bee49792-9c0b-49f7-9fce-5d9d631d045f"))
        .build();
  }
}

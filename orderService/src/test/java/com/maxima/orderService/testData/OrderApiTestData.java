package com.maxima.orderService.testData;

import com.maxima.orderService.dto.CategoryCreateDto;
import com.maxima.orderService.dto.CategoryDto;
import java.util.UUID;
import lombok.Getter;

/**
 * Класс с данными для теста
 */
public class OrderApiTestData {

  public static final String CATEGORY_UUID = "fcc49792-9c0b-49f7-9fce-5d9d631d042f";

  public static final String NON_EXISTING_CATEGORY_UUID = "00000000-0000-0000-0000-000000000000";

  public static final String CATEGORY_NAME = "Тестовая категория1";

  @Getter
  private static final CategoryCreateDto categoryCreateDto = new CategoryCreateDto(
      "Тестовая категория");

  @Getter
  private static final CategoryDto categoryDto = new CategoryDto(4L, UUID.fromString(
      "fcc49792-9c0b-49f7-9fce-5d9d631d042f"), "Чипсы");

  @Getter
  private static final CategoryDto categoryDtoForCreate = new CategoryDto(10L, UUID.fromString(
      "1cc49792-9c0b-49f7-9fce-5d9d631d042f"), "Тестовая категория");

  @Getter
  private static final CategoryDto categoryDtoForUpdate = new CategoryDto(10L, UUID.fromString(
      "1cc49792-9c0b-49f7-9fce-5d9d631d042f"), CATEGORY_NAME);

  @Getter
  private static final CategoryDto categoryDtoFirstInList = new CategoryDto(1L, UUID.fromString(
      "fcc49792-9c0b-49f7-9fce-5d9d631d045f"), "Напитки");
}

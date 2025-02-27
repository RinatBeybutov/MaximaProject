package com.maxima.userService.testData;

import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserViewDto;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Класс UserDtoTestData содержит статические методы и константы для создания тестовых данных для
 * DTO пользователей.
 */
public class UserDtoTestData {

  public static final UUID WRONG_UUID = UUID.fromString("974df0be-8fe6-4cb8-8e71-b307567c3e60");

  public static final String USER_NOT_FOUND_MESSAGE = "Пользователь с таким uuid %s не найден".formatted(WRONG_UUID);

  public static final int NUMBER_OF_USERS = 3;

  public static UserCreateDto userCreateDto() {
    return UserCreateDto.builder()
        .email("test@example.com")
        .name("Test User")
        .build();
  }

  public static UserCreateDto userUpdateDto() {
    return UserCreateDto.builder()
        .name("new name")
        .email("newemail@mail.com")
        .build();
  }

  public static UserViewDto createdViewDto() {
    return UserViewDto.builder()
        .name("Test User")
        .email("test@example.com")
        .build();
  }

  public static UserViewDto updatedUserDto() {
    return UserViewDto.builder()
        .name("new name")
        .email("newemail@mail.com")
        .build();
  }

  /**
   * Создает экземпляр UserViewDto с тестовыми данными для пользователя Владимира.
   *
   * @return UserViewDto с предустановленными значениями email, name, uuid и registeredAt.
   */
  public static UserViewDto vladimirUserDto() {
    return UserViewDto.builder()
        .email("vladimir@gmail.com")
        .name("Владимир")
        .uuid(UUID.fromString("39e89964-5f36-46c7-a6c1-324014b75463"))
        .registeredAt(LocalDate.parse("2021-01-01"))
        .build();
  }

}

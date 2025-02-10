package com.maxima.userService.testData;

import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserViewDto;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

public class UserDtoTestData {

  public static final String USER_NOT_FOUND_MESSAGE = "Пользователь с таким UUID не найден";

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

  public static UserViewDto vladimirUserDto() {
    return UserViewDto.builder()
        .email("vladimir@gmail.com")
        .name("Владимир")
        .uuid(UUID.fromString("39e89964-5f36-46c7-a6c1-324014b75463"))
        .registeredAt(Date.from(LocalDate.parse("2021-01-01")
                                    .atStartOfDay()
                                    .atZone(ZoneId.of("Europe/London"))
                                    .toInstant()))
        .build();
  }

}

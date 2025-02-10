package com.maxima.userService.testData;

import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserViewDto;

public class UserDtoTestData {
  public static UserCreateDto getUserCreateDto() {
    return UserCreateDto.builder()
        .email("test@example.com")
        .name("Test User")
        .build();
  }

  public static UserCreateDto getNewUserCreateDto() {
    return UserCreateDto.builder()
        .name("new name")
        .email("newemail@mail.com")
        .build();
  }

  public static UserViewDto getUserViewDto() {
    return UserViewDto.builder()
        .name("Test User")
        .email("test@example.com")
        .build();
  }

  public static UserViewDto getNewUserViewDto() {
    return UserViewDto.builder()
        .name("new name")
        .email("newemail@mail.com")
        .build();
  }

}

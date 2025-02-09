package com.maxima.userService.dto;

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

}

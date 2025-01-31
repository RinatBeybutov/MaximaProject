package com.maxima.userService.service;

import com.maxima.userService.dto.inside.UserInsideDto;
import com.maxima.userService.dto.out.UserOutDto;
import java.util.List;
import java.util.UUID;

public interface UserService {

  UserOutDto addUser(UserInsideDto userInsideDto);

  List<UserOutDto> getAllUsers();

  UserOutDto getUserByUUID(UUID uuid);

  UserOutDto updateUserByUUID(UserInsideDto userInsideDto, UUID uuid);

  void deleteUserByUUID(UUID uuid);
}

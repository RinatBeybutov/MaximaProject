package com.maxima.userService.service;

import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserViewDto;
import java.util.List;
import java.util.UUID;

/**
 * Интерфейс сервиса для управления пользователями.
 *
 */
public interface UserService {

  UserViewDto create(UserCreateDto userCreateDto);

  List<UserViewDto> getList();

  UserViewDto getOne(UUID uuid);

  UserViewDto update(UserCreateDto userCreateDto, UUID uuid);

  void delete(UUID uuid);
}
package com.maxima.userService.service;

import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserViewDto;
import java.util.List;
import java.util.UUID;
import org.springframework.http.ResponseEntity;

public interface UserService {

  ResponseEntity<UserViewDto> create(UserCreateDto userCreateDto);

  ResponseEntity<List<UserViewDto>> getList();

  ResponseEntity<UserViewDto> getOne(UUID uuid);

  ResponseEntity<UserViewDto> update(UserCreateDto userCreateDto, UUID uuid);

  ResponseEntity<Void> delete(UUID uuid);
}
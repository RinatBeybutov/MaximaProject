package com.maxima.userService.controller;

import com.maxima.userService.dto.UserCreateDto;
import com.maxima.userService.dto.UserViewDto;
import com.maxima.userService.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Пользователи", description = "Операции CRUD с пользователями ")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @Operation(summary = "Создание пользователя")
  @PostMapping
  public ResponseEntity<UserViewDto> create(@Valid @RequestBody UserCreateDto userCreateDto) {
    return ResponseEntity.ok(userService.create(userCreateDto));
  }

  @Operation(summary = "Получение списка пользователей")
  @GetMapping
  public ResponseEntity<List<UserViewDto>> getList() {
    return ResponseEntity.ok(userService.getList());
  }

  @Operation(summary = "Получение пользователя по UUID")
  @GetMapping("/{uuid}")
  public ResponseEntity<UserViewDto> getOne(@PathVariable UUID uuid) {
    return ResponseEntity.ok(userService.getOne(uuid));
  }

  @Operation(summary = "Обновление пользователя по UUID")
  @PutMapping("/{uuid}")
  public ResponseEntity<UserViewDto> update(
      @Valid @RequestBody UserCreateDto userCreateDto,
      @PathVariable UUID uuid) {
    return ResponseEntity.ok(userService.update(userCreateDto, uuid));
  }

  @Operation(summary = "Удаление пользователя по UUID")
  @DeleteMapping("/{uuid}")
  public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
    userService.delete(uuid);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

package com.maxima.orderService.controllers;

import com.maxima.orderService.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import com.maxima.orderService.dto.*;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static com.maxima.orderService.config.ApiConfig.CATEGORIES;

import io.swagger.v3.oas.annotations.*;

/**
 * Класс Рест Контроллера для реализации API для работы с Категориями
 */
@Tag(name = "Категории", description = "Операции CRUD для работы с Категориями")
@RestController
@RequestMapping(CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {

  private final CategoryService service;

  /**
   * Создать новую категорию
   *
   * @param categoryCreateDto название категории
   */
  @Operation(summary = "Создать категорию")
  @PostMapping
  public ResponseEntity<CategoryDto> create(@RequestBody CategoryCreateDto categoryCreateDto) {
    return ResponseEntity.ok(service.create(categoryCreateDto));
  }

  /**
   * Получить список категорий
   */
  @Operation(summary = "Получить список категорий")
  @GetMapping
  public ResponseEntity<List<CategoryDto>> getList() {
    return ResponseEntity.ok(service.getList());
  }

  /**
   * Получить категорию по UUID
   *
   * @param uuid идентификатор категории
   */
  @Operation(summary = "Получить категорию по UUID")
  @GetMapping("/{uuid}")
  public ResponseEntity<CategoryDto> find(
      @PathVariable("uuid") @Parameter(example = "fcc49792-9c0b-49f7-9fce-5d9d631d045f", required = true) UUID uuid) {
    return ResponseEntity.ok(service.find(uuid));
  }

  /**
   * Обновить категорию по UUID
   *
   * @param categoryCreateDto идентификатор категории
   */
  @Operation(summary = "Обновить категорию по UUID")
  @PutMapping("/{uuid}")
  public ResponseEntity<CategoryDto> update(
      @PathVariable("uuid") @Parameter(example = "fcc49792-9c0b-49f7-9fce-5d9d631d045f", required = true) UUID uuid,
      @RequestBody CategoryCreateDto categoryCreateDto) {
    return ResponseEntity.ok(service.update(uuid, categoryCreateDto));
  }

  /**
   * Удалить категорию по UUID
   *
   * @param uuid идентификатор категории
   */
  @Operation(summary = "Удалить категорию по UUID")
  @DeleteMapping("/{uuid}")
  public ResponseEntity<Void> delete(
      @PathVariable("uuid") @Parameter(example = "fcc49792-9c0b-49f7-9fce-5d9d631d045f", required = true) UUID uuid) {
    service.delete(uuid);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

package com.maxima.orderService.controllers;

import static com.maxima.orderService.config.ApiConfig.PRODUCTS;

import com.maxima.orderService.dto.ProductCreateDto;
import com.maxima.orderService.dto.ProductViewDto;
import com.maxima.orderService.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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

/**
 * Класс Рест контроллера для реализации Api для работы с продуктами.
 */
@Tag(name = "Продукты", description = "Операции CRUD для работы с продуктами")
@RestController
@RequestMapping(PRODUCTS)
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;

  /**
   * Создать новый продукт.
   *
   * @param productCreateDto название продукта
   */
  @PostMapping
  @Operation(summary = "Создать продукт")
  public ResponseEntity<ProductViewDto> create(@RequestBody ProductCreateDto productCreateDto) {
    return ResponseEntity.ok(service.create(productCreateDto));
  }

  /**
   * Получить список продуктов.
   */
  @GetMapping
  @Operation(summary = "Получить список продуктов")
  public ResponseEntity<List<ProductViewDto>> getList() {
    return ResponseEntity.ok(service.getList());
  }

  /**
   * Получить продукт по его UUID.
   *
   * @param uuid идентификатор продукта
   */
  @GetMapping("/{uuid}")
  @Operation(summary = "Получение продукта по его UUID")
  public ResponseEntity<ProductViewDto> find(
      @PathVariable("uuid") @Parameter(example = "1867e5bc-3b07-45f8-b2a6-be1e01274adc",
          required = true) UUID uuid) {
    return ResponseEntity.ok(service.find(uuid));
  }

  /**
   * Обновление продукта по его UUID.
   *
   * @param productCreateDto идентификатор продукта
   */
  @PutMapping("/{uuid}")
  @Operation(summary = "Обновление продукта по его UUID")
  public ResponseEntity<ProductViewDto> update(
      @PathVariable("uuid") @Parameter(example = "1867e5bc-3b07-45f8-b2a6-be1e01274adc",
          required = true) UUID uuid,
      @RequestBody ProductCreateDto productCreateDto) {
    return ResponseEntity.ok(service.update(uuid, productCreateDto));
  }

  /**
   * Удаление продукта по его UUID.
   *
   * @param uuid идентификатор продукта
   */
  @DeleteMapping("/{uuid}")
  @Operation(summary = "Удаление продукта по его UUID")
  public ResponseEntity<Void> delete(
      @PathVariable("uuid") @Parameter(example = "1867e5bc-3b07-45f8-b2a6-be1e01274adc",
          required = true) UUID uuid) {
    service.delete(uuid);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

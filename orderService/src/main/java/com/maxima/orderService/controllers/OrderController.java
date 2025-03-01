package com.maxima.orderService.controllers;

import static com.maxima.orderService.config.ApiConfig.ORDERS;

import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderDto;
import com.maxima.orderService.service.OrderService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Класс Рест Контроллера для реализации API для работы с Заказами
 */
@Tag(name = "Заказы", description = "Операции CRUD для работы с Заказами")
@RestController
@RequestMapping(ORDERS)
@RequiredArgsConstructor
public class OrderController {

  private final OrderService service;

  /**
   * Создать новый заказ
   *
   * @param orderCreateDto название заказа
   */
  @Operation(summary = "Создать заказ")
  @PostMapping
  public ResponseEntity<OrderDto> create(@RequestBody OrderCreateDto orderCreateDto) {
    return ResponseEntity.ok(service.create(orderCreateDto));
  }

  /**
   * Получить список заказов по UUID пользователя
   */
  @Operation(summary = "Получить список заказов по UUID пользователя")
  @GetMapping
  public ResponseEntity<List<OrderDto>> getList(@RequestParam(name = "user") UUID uuid) {
    return ResponseEntity.ok(service.getList(uuid));
  }

  /**
   * Получить заказ по UUID
   *
   * @param uuid идентификатор заказа
   */
  @Operation(summary = "Получить заказ по UUID")
  @GetMapping("/{uuid}")
  public ResponseEntity<OrderDto> find(
      @PathVariable("uuid") @Parameter(example = "acc49792-9c0b-49f7-9fce-5d9d631d045f", required = true) UUID uuid) {
    return ResponseEntity.ok(service.find(uuid));
  }

  /**
   * Обновить заказ по UUID
   *
   * @param orderCreateDto идентификатор заказа
   */
  @Operation(summary = "Обновить заказ по UUID")
  @PutMapping("/{uuid}")
  public ResponseEntity<OrderDto> update(
      @PathVariable("uuid") @Parameter(example = "acc49792-9c0b-49f7-9fce-5d9d631d045f", required = true) UUID uuid,
      @RequestBody OrderCreateDto orderCreateDto) {
    return ResponseEntity.ok(service.update(uuid, orderCreateDto));
  }

  /**
   * Удалить заказ по UUID
   *
   * @param uuid идентификатор заказа
   */
  @Operation(summary = "Удалить заказ по UUID")
  @DeleteMapping("/{uuid}")
  public ResponseEntity<Void> delete(
      @PathVariable("uuid") @Parameter(example = "acc49792-9c0b-49f7-9fce-5d9d631d045f", required = true) UUID uuid) {
    service.delete(uuid);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}

package com.maxima.orderService.controllers;

import static com.maxima.orderService.config.ApiConfig.ORDERS;

import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderUpdateDto;
import com.maxima.orderService.dto.OrderViewDto;
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
   * @param orderCreateDto дто заказа с фтонтэнда
   */
  @Operation(summary = "Создать заказ")
  @PostMapping
  public ResponseEntity<OrderViewDto> create(@RequestBody OrderCreateDto orderCreateDto) {
    return ResponseEntity.ok(service.create(orderCreateDto));
  }

  /**
   * Получить список заказов по UUID пользователя
   */
  @Operation(summary = "Получить список заказов по UUID пользователя")
  @GetMapping
  public ResponseEntity<List<OrderViewDto>> getList(@RequestParam(name = "user") UUID userUuid) {
    return ResponseEntity.ok(service.toList(userUuid));
  }

  /**
   * Обновить заказ по UUID
   *
   * @param uuid идентификатор заказа
   */
  @Operation(summary = "Обновить заказ по UUID")
  @PutMapping("/{uuid}")
  public ResponseEntity<OrderViewDto> update(
      @PathVariable("uuid") @Parameter(example = "acc49792-9c0b-49f7-9fce-5d9d631d045f", required = true) UUID uuid,
      @RequestBody OrderUpdateDto orderUpdateDto) {
    return ResponseEntity.ok(service.update(uuid, orderUpdateDto));
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

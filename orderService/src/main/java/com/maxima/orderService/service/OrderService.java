package com.maxima.orderService.service;

import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderDto;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для реализации работы с Категориями
 */
public interface OrderService {

  /**
   * Создать категорию
   */
  OrderDto create(OrderCreateDto dto);

  /**
   * Найти категорию по uuid
   */
  OrderDto find(UUID uuid);

  /**
   * Обновить категорию по uuid
   */
  OrderDto update(UUID uuid, OrderCreateDto categoryInputDto);

  /**
   * Удалить категорию по uuid
   */
  void delete(UUID uuid);

  /**
   * Получить список всех категорий
   */
  List<OrderDto> getList(UUID uuid);
}


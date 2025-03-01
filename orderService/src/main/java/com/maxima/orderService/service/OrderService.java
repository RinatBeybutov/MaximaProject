package com.maxima.orderService.service;

import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderDto;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для реализации работы с Заказами
 */
public interface OrderService {

  /**
   * Создать Заказ
   */
  OrderDto create(OrderCreateDto dto);

  /**
   * Найти Заказ по uuid
   */
  OrderDto find(UUID uuid);

  /**
   * Обновить Заказ по uuid
   */
  OrderDto update(UUID uuid, OrderCreateDto categoryInputDto);

  /**
   * Удалить Заказ по uuid
   */
  void delete(UUID uuid);

  /**
   * Получить список всех Заказов
   */
  List<OrderDto> getList(UUID uuid);
}


package com.maxima.orderService.service;

import com.maxima.orderService.dto.OrderCreateDto;
import com.maxima.orderService.dto.OrderDto;
import com.maxima.orderService.dto.OrderUpdateDto;
import com.maxima.orderService.dto.OrderViewDto;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для реализации работы с Заказами
 */
public interface OrderService {

  /**
   * Создать Заказ
   */
  OrderViewDto create(OrderCreateDto dto);

  /**
   * Найти Заказ по uuid
   */
  OrderDto find(UUID uuid);

  /**
   * Обновить Заказ по uuid
   */
  OrderDto update(UUID uuid, OrderUpdateDto orderUpdateDto);

  /**
   * Удалить Заказ по uuid
   */
  void delete(UUID uuid);

  /**
   * Получить список всех Заказов
   */
  List<OrderDto> toList(UUID uuid);
}


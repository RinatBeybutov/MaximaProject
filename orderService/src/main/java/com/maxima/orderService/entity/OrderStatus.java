package com.maxima.orderService.entity;

import lombok.Getter;

/**
 * Перечисление со статусами заказа
 */
public enum OrderStatus {
  CREATED(1),
  IN_PROGRESS(2),
  CANCELED(3),
  FINISHED(4);

  @Getter
  private final int value;

  OrderStatus(int status) {
    this.value = status;
  }

  /**
   * Метод для получения значения перечисления по целому значению
   */
  public static OrderStatus forInt(int value) {
    for (var status : values()) {
      if (status.getValue() == value) {
        return status;
      }
    }
    throw new IllegalArgumentException("Неверное значение индекса для enum OrderStatus");
  }
}

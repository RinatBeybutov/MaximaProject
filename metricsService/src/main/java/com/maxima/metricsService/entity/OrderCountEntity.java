package com.maxima.metricsService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

/**
 * Сущность для хранения информации о количестве заказов в Postgres
 */
@Data
@Entity
@Table(schema = "public", name = "order_count")//надо заменить схему после создания миграций
@IdClass(OrderCountEntity.OrderCountId.class)
public class OrderCountEntity implements Serializable {

  @Id
  @Column(name = "order_date", nullable = false)
  private LocalDate orderDate;

  @Id
  @Column(name = "order_hour")
  private Integer orderHour;

  @Column(name = "order_count", nullable = false)
  private Integer orderCount;

  /**
   * Класс составного ключа
   * Состоит из даты и целого числа - часа
   */
  @Data
  public static class OrderCountId implements Serializable {

    private LocalDate orderDate;

    private Integer orderHour;
  }
}

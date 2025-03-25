package com.maxima.metricsService.testData;

import com.maxima.metricsService.entity.OrderCountEntity;
import java.time.LocalDate;
import com.maxima.metricsService.repository.OrderCountRepositoryTest;

/**
 * Класс с тестовыми данными для {@link OrderCountRepositoryTest}
 */
public class OrderCountTestData {

  /**
   * Создает экземпляр {@link OrderCountEntity} с тестовыми данными:
   * <ul>
   *   <li>Дата: 20 января 2024 года</li>
   *   <li>Час: 14 (14:00)</li>
   *   <li>Количество заказов: 10</li>
   * </ul>
   *
   * @return Предварительно настроенный экземпляр {@link OrderCountEntity}
   */
  public static OrderCountEntity orderCount() {
    var orderCount = new OrderCountEntity();
    orderCount.setOrderDate(LocalDate.of(2024, 1, 20));
    orderCount.setOrderHour(14);
    orderCount.setOrderCount(10);

    return orderCount;
  }

  /**
   * Создает экземпляр {@link OrderCountEntity} с тестовыми данными:
   * <ul>
   *   <li>Дата: 20 января 2024 года</li>
   *   <li>Час: 12 (14:00)</li>
   *   <li>Количество заказов: 3</li>
   * </ul>
   *
   * @return Предварительно настроенный экземпляр {@link OrderCountEntity}
   */
  public static OrderCountEntity otherOrderCount() {
    var orderCount = new OrderCountEntity();
    orderCount.setOrderDate(LocalDate.of(2024, 1, 20));
    orderCount.setOrderHour(12);
    orderCount.setOrderCount(3);

    return orderCount;
  }
}

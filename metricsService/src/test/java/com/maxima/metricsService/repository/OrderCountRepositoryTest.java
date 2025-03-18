package com.maxima.metricsService.repository;

import static com.maxima.metricsService.testData.OrderCountTestData.otherOrderCount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.maxima.metricsService.testData.OrderCountTestData.orderCount;

import com.maxima.metricsService.config.TestContainersConfig;
import com.maxima.metricsService.entity.OrderCountEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

/**
 * Класс для тестирования методов репозитория {@link OrderCountRepository}
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("Тест Jpa репозитория для сущности OrderCount")
public class OrderCountRepositoryTest extends TestContainersConfig {

  @Autowired
  private OrderCountRepository repository;

  @Test
  @DisplayName("Тест сохранения, получения и удаления данных")
  void saveGetDeleteOrderCount() {
    var entity = orderCount();

    repository.save(entity);

    var retrievedOrderCount = repository
        .findByOrderDateAndOrderHour(entity.getOrderDate(),
                                     entity.getOrderHour());

    assertThat(retrievedOrderCount).isPresent();

    var record = retrievedOrderCount.get();

    assertEquals(record.getOrderDate(), entity.getOrderDate());

    assertEquals(record.getOrderHour(), entity.getOrderHour());

    assertEquals(record.getOrderCount(), entity.getOrderCount());

    repository.deleteByOrderDateAndOrderHour(entity.getOrderDate(), entity.getOrderHour());
  }

  @Test
  @DisplayName("Тест поиска по дате")
  void findByOrderDate() {
    var firstEntity = orderCount();
    repository.save(firstEntity);

    var secondEntity = otherOrderCount();
    repository.save(secondEntity);

    var retrievedRecords = repository.findByOrderDate(firstEntity.getOrderDate());

    assertThat(retrievedRecords).hasSize(2);
    assertThat(retrievedRecords)
        .extracting(OrderCountEntity::getOrderCount)
        .containsExactlyInAnyOrder(10, 3);

    repository.deleteByOrderDateAndOrderHour(firstEntity.getOrderDate(),
                                             firstEntity.getOrderHour());
    repository.deleteByOrderDateAndOrderHour(secondEntity.getOrderDate(),
                                             secondEntity.getOrderHour());
  }
}
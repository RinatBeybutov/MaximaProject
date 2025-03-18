package com.maxima.metricsService.repository;

import com.maxima.metricsService.entity.OrderCountEntity;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностью {@link OrderCountEntity}
 */
public interface OrderCountRepository extends JpaRepository<OrderCountEntity, OrderCountEntity.OrderCountId> {

  Optional<OrderCountEntity> findByOrderDateAndOrderHour(LocalDate orderDate, Integer orderHour);

  List<OrderCountEntity> findByOrderDate(LocalDate orderDate);

  void deleteByOrderDateAndOrderHour(LocalDate orderDate, Integer orderHour);
}

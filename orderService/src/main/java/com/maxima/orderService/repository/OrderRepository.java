package com.maxima.orderService.repository;

import com.maxima.orderService.entity.CategoryEntity;
import com.maxima.orderService.entity.OrderEntity;
import com.maxima.orderService.exceptions.ResponseException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностями заказов. Предоставляет методы для выполнения операций CRUD
 * с заказами.
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
  Optional<OrderEntity> findByUuid(UUID uuid);

  default OrderEntity getByUuid(UUID uuid) throws ResponseException {
    return findByUuid(uuid).orElseThrow(ResponseException::new);
  }
}

package com.maxima.orderService.repository;

import com.maxima.orderService.entity.ProductEntity;
import com.maxima.orderService.exceptions.ResponseException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностями продуктов. Предоставляет методы для выполнения операций CRUD
 * с продуктами.
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

  Optional<ProductEntity> findByUuid(UUID uuid);

  default ProductEntity getByUuid(UUID uuid) throws ResponseException {
    return findByUuid(uuid).orElseThrow(ResponseException::new);
  }
}

package com.maxima.orderService.repository;

import com.maxima.orderService.entity.CategoryEntity;
import com.maxima.orderService.exceptions.ResponseException;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностями категорий. Предоставляет методы для выполнения операций CRUD
 * с категориями.
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
  Optional<CategoryEntity> findByUuid(UUID uuid);

  default CategoryEntity getByUuid(UUID uuid) throws ResponseException {
    return findByUuid(uuid).orElseThrow(ResponseException::new);
  }
}

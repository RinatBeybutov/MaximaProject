package com.maxima.orderService.repository;

import com.maxima.orderService.entity.CategoryEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностями категорий.
 * Предоставляет методы для выполнения операций CRUD с категориями.
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

  CategoryEntity getByUuid(UUID uuid);
}

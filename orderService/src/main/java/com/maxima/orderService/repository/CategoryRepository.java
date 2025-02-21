package com.maxima.orderService.repository;

import com.maxima.orderService.entity.CategoryEntity;

import java.util.List;
import java.util.Optional;

import java.util.UUID;

import com.maxima.orderService.exceptions.ResponseException;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностями категорий.
 * Предоставляет методы для выполнения операций CRUD с категориями.
 */
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity save(CategoryEntity entity);

    void delete(CategoryEntity entity);

    Optional<CategoryEntity> findByUuid(UUID uuid);

    List<CategoryEntity> findAll();

    default CategoryEntity getByUuid(UUID uuid) throws ResponseException {
        return findByUuid(uuid).orElseThrow(() -> new ResponseException());
    }
}

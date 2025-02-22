package com.maxima.orderService.repository;

import com.maxima.orderService.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностями продуктов. Предоставляет методы для выполнения операций CRUD
 * с продуктами.
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}

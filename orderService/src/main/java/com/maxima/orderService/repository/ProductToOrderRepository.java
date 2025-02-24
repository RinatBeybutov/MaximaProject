package com.maxima.orderService.repository;

import com.maxima.orderService.entity.ProductEntity;
import com.maxima.orderService.entity.ProductToOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сущностями класса ProductToOrder.
 */
public interface ProductToOrderRepository extends JpaRepository<ProductToOrderEntity, Long> {

}

package com.maxima.orderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая продукты в системе.
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "order_service", name = "products")
public class ProductEntity extends BaseEntity {

  @Column(name = "name")
  private String name;

  @Column(name = "category_id")
  private Long categoryId;
}

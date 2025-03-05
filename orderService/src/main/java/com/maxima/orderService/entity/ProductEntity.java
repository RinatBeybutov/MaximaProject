package com.maxima.orderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

  @Column(name = "category_id", updatable = false, insertable = false)
  private Long categoryid;

  @ManyToOne(fetch = FetchType.LAZY)
  private CategoryEntity category;
}

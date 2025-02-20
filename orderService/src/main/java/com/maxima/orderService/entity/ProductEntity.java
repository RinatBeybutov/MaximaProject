package com.maxima.orderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "order_service", name = "product")
public class ProductEntity extends BaseEntity {

  private String name;

  @Column(name = "category_id")
  private Integer categoryId;
}

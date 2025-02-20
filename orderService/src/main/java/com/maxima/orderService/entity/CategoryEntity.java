package com.maxima.orderService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Сущность, представляющая категорию в системе.
 */
@Data
@Entity
@Table(name = "categories", schema = "order_service")
public class CategoryEntity extends BaseEntity {

  private String name;

}

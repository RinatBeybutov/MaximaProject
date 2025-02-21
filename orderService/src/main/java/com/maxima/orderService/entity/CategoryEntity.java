package com.maxima.orderService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Сущность, представляющая категорию в системе.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories", schema = "order_service")
public class CategoryEntity extends BaseEntity {

    private String name;

}
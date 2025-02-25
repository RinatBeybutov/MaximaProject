package com.maxima.orderService.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
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
@Table(schema = "order_service", name = "product")
public class ProductEntity extends BaseEntity {

  private String name;

  @Column(name = "category_id")
  private Integer categoryId;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "product_to_order",
      joinColumns = {
          @JoinColumn(name = "product_id", referencedColumnName = "id")
      },
      inverseJoinColumns = {
          @JoinColumn(name = "order_id", referencedColumnName = "id"),
      }
  )
  private Set<OrderEntity> orders = new HashSet<>();
}

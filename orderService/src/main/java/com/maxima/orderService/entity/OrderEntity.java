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
import java.util.Date;
import java.util.UUID;

/**
 * Сущность заказа
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", schema = "order_service")
public class OrderEntity extends BaseEntity {
  @Column(name = "date")
  private Date createdAt;

  @Column(name = "user_uuid")
  private UUID userUuid;

  @Column(name = "status")
  private OrderStatus status;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "product_to_order",
      joinColumns = {
        @JoinColumn(name = "order_id", referencedColumnName = "id"),
      },
      inverseJoinColumns = {
        @JoinColumn(name = "product_id", referencedColumnName = "id")
      }
  )
  private Set<ProductEntity> products = new HashSet<>();
}

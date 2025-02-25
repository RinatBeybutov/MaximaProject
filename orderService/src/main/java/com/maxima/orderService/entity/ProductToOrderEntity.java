package com.maxima.orderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Сущность связи продукта и заказа
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_to_order", schema = "order_service")
public class ProductToOrderEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "product_id")
  private Long productId;

  @Column(name = "count")
  private Long count;

  @Column(name = "order_id")
  private Long orderId;
}

package com.maxima.orderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
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

  @ManyToOne
  @JoinColumn(name = "product_id")
  @JoinTable(name = "product")
  private ProductEntity product;

  @ManyToOne
  @JoinColumn(name = "order_id")
  @JoinTable(name = "products")
  private OrderEntity order;

  @Column(name = "count")
  private Long count;
}

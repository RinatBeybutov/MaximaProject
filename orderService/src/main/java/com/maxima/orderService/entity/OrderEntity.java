package com.maxima.orderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;
import lombok.Setter;

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
  private LocalDateTime createdAt;

  @Column(name = "user_uuid")
  private UUID userUuid;

  @Column(name = "status")
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private Integer status;

  public void setStatus(OrderStatus orderStatus) {
    status = orderStatus.getValue();
  }

  public OrderStatus getStatus() {
    return OrderStatus.fromValue(status);
  }
}
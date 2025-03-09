package com.maxima.orderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import java.util.UUID;
import lombok.Setter;

/**
 * Сущность заказа
 */
@Data
@Entity
@Table(name = "orders", schema = "order_service")
public class OrderEntity extends BaseEntity {

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "user_uuid")
  private UUID userUuid;

  @Column(name = "status")
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private Integer status = 1;

  public void setStatus(OrderStatus orderStatus) {
    status = orderStatus.getValue();
  }

  public OrderStatus getStatus() {
    return OrderStatus.fromValue(status);
  }

  public OrderEntity(){
    createdAt = LocalDateTime.now();
    status = 1;
  }
}
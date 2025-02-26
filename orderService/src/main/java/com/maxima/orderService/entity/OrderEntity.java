package com.maxima.orderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.Date;
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
  private Date createdAt;

  @Column(name = "user_uuid")
  private UUID userUuid;

  @Column(name = "status")
  @Getter(AccessLevel.PRIVATE)
  @Setter(AccessLevel.PRIVATE)
  private Integer status;

  public void setStatus(OrderStatus orderStatus){
    status = orderStatus.getStatus();
  }
  public OrderStatus getStatus(){
    return OrderStatus.forInt(status);
  }
}
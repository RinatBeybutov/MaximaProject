package com.maxima.orderService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products", schema = "order_service")
public class OrderEntity extends BaseEntity {
  @Column(name = "date")
  private Date createdAt;

  @Column(name = "user_uuid")
  private UUID user_uuid;

  @Column(name = "status")
  private OrderStatus status;
}

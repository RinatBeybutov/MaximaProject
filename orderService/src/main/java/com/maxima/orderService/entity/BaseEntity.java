package com.maxima.orderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import java.util.UUID;
import lombok.Data;

/**
 * Базовый класс для всех сущностей, предоставляющий общие поля и функциональность.
 */
@Data
@MappedSuperclass
public class BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "uuid", unique = true)
  private UUID uuid;

  @PrePersist
  private void generateUuid() {
    setUuid(UUID.randomUUID());
  }
}
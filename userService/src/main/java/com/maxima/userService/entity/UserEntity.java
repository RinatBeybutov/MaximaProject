package com.maxima.userService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;


@Data
@Entity
@Table(schema = "user_service", name = "users")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "uuid", unique = true)
  private UUID uuid;

  private String name;

  @Column(name = "registered_at")
  private LocalDate registeredAt;

  private String email;

  @PrePersist
  private void generateUuid() {
    setUuid(UUID.randomUUID());
  }
}

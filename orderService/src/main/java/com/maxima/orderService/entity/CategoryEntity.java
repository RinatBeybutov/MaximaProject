package com.maxima.orderService.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "categories", schema = "order_service")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", unique = true)
    private UUID uuid;

    private String name;

    @PrePersist
    private void generateUuid() {
        setUuid(UUID.randomUUID());
    }
}

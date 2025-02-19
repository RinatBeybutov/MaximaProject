package com.maxima.orderService.entity;

import jakarta.persistence.*;

import java.util.UUID;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @Column(name="id",nullable=false)   
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sgu")
    @SequenceGenerator(name="sgu",sequenceName="order_service.cat_id_seq",allocationSize=1)
    private Long id;

    @Column(columnDefinition = "uuid", unique = true)
    UUID uuid;
}

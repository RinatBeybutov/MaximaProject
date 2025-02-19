package com.maxima.orderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.*;

import java.util.UUID;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {
  /*@Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "uuid", unique = true)
  private UUID uuid;*/

    @Id
    @Column(name="id",nullable=false)   
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sgu")
    @SequenceGenerator(name="sgu",sequenceName="order_service.cat_id_seq",allocationSize=1)
    private Long id;

    @Column(columnDefinition = "uuid", unique = true)
    UUID uuid;
}

package com.maxima.orderService.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "categories", schema = "order_service")
public class CategoryEntity extends BaseEntity{

    private String name;

}

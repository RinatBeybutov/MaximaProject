package com.maxima.orderService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories", schema = "order_service")
public class CategoryEntity extends BaseEntity{

    private String name;

}

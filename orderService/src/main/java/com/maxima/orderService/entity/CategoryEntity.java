package com.maxima.orderService.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories", schema = "order_service")
public class CategoryEntity extends BaseEntity{
    private String name;

    /*public CategoryEntity(String name){
        this.name=name;
    }*/
}

package com.maxima.orderService.dto;

import lombok.*;

/**
* Дто сущности категории
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private int i;
    private String name;

    public String getName(){
        return name;
    }

    public CategoryDto(String name){
        this.name=name;
        i=0;
    }

    public CategoryDto(int i,String name){
        this.name=name;
        this.i=i;
    }
}

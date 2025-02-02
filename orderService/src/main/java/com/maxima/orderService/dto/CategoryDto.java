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
    private long i;
    private String name;

    public void setI(long i){
        this.i=i;
    }

    public void setName(String name){
        this.name=name;
    }

    public long getI(){
        return i;
    }

    public String getName(){
        return name;
    }

    public CategoryDto(String name){
        this.name=name;
        i=0;
    }

    public CategoryDto(long i,String name){
        this.name=name;
        this.i=i;
    }
}

package com.maxima.orderService.dto;

import lombok.*;
import java.util.UUID;

/**
* Дто сущности категории
*/
//@Data
//@Builder
@NoArgsConstructor
//@AllArgsConstructor
public class CategoryDto {
    private long i;
    private UUID uuid;
    private String name;

    public void setUuid(UUID uuid){
        if(uuid==null){
            this.uuid=null; 
        }
        else this.uuid=UUID.fromString(uuid.toString());
    }

    public UUID getUuid(){
        return uuid;
    }


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

    /*public CategoryDto(String name){
        this.name=name;
        i=0;
    }*/

    public CategoryDto(long i,UUID uuid,String name){
        this.name=name;
        if(uuid==null){
            this.uuid=null; 
        }
        else this.uuid=UUID.fromString(uuid.toString());
        this.i=i;
    }
}

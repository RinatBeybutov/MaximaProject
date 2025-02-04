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
    private long id;
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


    public void setId(long id){
        this.id=id;
    }

    public void setName(String name){
        this.name=name;
    }

    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public CategoryDto(long id,UUID uuid,String name){
        this.name=name;
        if(uuid==null){
            this.uuid=null; 
        }
        else this.uuid=UUID.fromString(uuid.toString());
        this.id=id;
    }
}

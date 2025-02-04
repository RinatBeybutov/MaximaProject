package com.maxima.orderService.dto;

import lombok.*;
import java.util.UUID;

/**
* Дто сущности категории для ввода с фронтэнда
*/
@NoArgsConstructor
public class CategoryInputDto {
    @Getter
    private UUID uuid;

    @Setter
    @Getter
    private String name;

    public void setUuid(UUID uuid){
        if(uuid==null){
            this.uuid=null; 
        }
        else this.uuid=UUID.fromString(uuid.toString());
    }

    public CategoryInputDto(UUID uuid,String name){
        this.name=name;
        if(uuid==null){
            this.uuid=null; 
        }
        else this.uuid=UUID.fromString(uuid.toString());
    }
}

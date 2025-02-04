package com.maxima.orderService.dto;

import lombok.*;
import java.util.UUID;

/**
* Дто сущности категории
*/
@NoArgsConstructor
public class CategoryDto {
    @Setter
    @Getter
    private long id;

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

    public CategoryDto(long id,UUID uuid,String name){
        this.name=name;
        if(uuid==null){
            this.uuid=null; 
        }
        else this.uuid=UUID.fromString(uuid.toString());
        this.id=id;
    }
}

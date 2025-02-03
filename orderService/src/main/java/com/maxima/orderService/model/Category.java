package com.maxima.orderService.model;

import jakarta.persistence.*;
import lombok.*;

/**
* Класс сущности категории
*/
@Getter
@Setter
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="category")
public class Category {
    @Id
    @Column(name="id",nullable=false)   
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sgu")
    @SequenceGenerator(name="sgu",sequenceName="cat_id_seq",allocationSize=1)
    private long i;

    @Column
    private String name;

    public long getI(){
        return i; 
    } 
    public void setName(String name){
        this.name=name; 
    } 
    public String getName(){
        return name; 
    } 

    public Category(){
        this.name="";
        this.i=0L;
    }
    public Category(long i, String name){
        this.name=name;
        this.i=i;
    }
}

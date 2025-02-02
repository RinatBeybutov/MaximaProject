package com.maxima.orderService.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxima.orderService.model.*;
import com.maxima.orderService.repo.*;
import com.maxima.orderService.dto.CategoryDto;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import com.maxima.orderService.util.ResponseException;

import java.util.stream.StreamSupport;

/**
* Класс Сервиса для реализации работы с Категориями
*/
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository cr;

    /**
     * Создать категорию
     */
    @Transactional
    public void createCategory(CategoryDto c){                                System.out.println(">>cs.n()-B");
        cr.save(new Category(0,c.getName()));
				                   System.out.println(">>cc-E");
    }
    /**
     * Найти категорию по id
     */
    @Transactional
    public Category find(int uuid) throws Exception{                        System.out.println(">>cs.f()-B");
        return cr.findById(uuid).orElseThrow( () -> new Exception() );
				                   //System.out.println(">>fc-E");
    }

    /**
     * Обновить категорию по id
     */
    @Transactional
    public void update(int uuid,String name) throws Exception{                     System.out.println(">>cs.u()-B");
        Category c=find(uuid);
        c.setName(name);
        cr.save(c);
				                   //System.out.println(">>uc-E");
    }

    /**
     * Удалить категорию по id
     */
    @Transactional
    public void delete(int uuid){                System.out.println(">>cs.d()-B");
        cr.deleteById(uuid);
				                   //System.out.println(">>ut-E");
    }

    /**
     * Получить список всех категорий
     */
    @Transactional
    public List<CategoryDto> index(){                System.out.println(">>cs.i()-B");
        /*List<Category> tl=cr.index();

        if(tl==null) throw new ResponseException(); //return null;   
        return tl.stream()
          .map( t -> new CategoryDto(t.getId(),t.getName()) ).collect(Collectors.toList());
        */

        return StreamSupport.stream(cr.findAll().spliterator(), false)
               .map( t -> new CategoryDto(t.getI(),t.getName()) ).collect(Collectors.toList());
        //return null;
    }

}

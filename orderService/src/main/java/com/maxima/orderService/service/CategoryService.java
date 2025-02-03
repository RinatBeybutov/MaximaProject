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
    public CategoryDto create(CategoryDto c){                                System.out.println(">>cs.n()-B");
        Category ct=cr.save(new Category(0,c.getName()));
				                   System.out.println(">>cc-E");
        return new CategoryDto(ct.getI(),ct.getName());
    }
    /**
     * Найти категорию по id
     */
    @Transactional
    public Category find(long uuid) throws ResponseException{                        System.out.println(">>cs.f()-B");
        return cr.findById(uuid).orElseThrow( () -> new ResponseException() );
				                   //System.out.println(">>fc-E");
    }

    /**
     * Обновить категорию по id
     */
    @Transactional
    public void update(long uuid,String name) throws ResponseException{                     System.out.println(">>cs.u()-B");
        Category c=find(uuid);
        c.setName(name);
        cr.save(c);
				                   //System.out.println(">>uc-E");
    }

    /**
     * Удалить категорию по id
     */
    @Transactional
    public void delete(long uuid) throws ResponseException{                System.out.println(">>cs.d()-B");
        if(!cr.existsById(uuid)) throw new ResponseException();
        cr.deleteById(uuid);
				                   //System.out.println(">>ut-E");
    }

    /**
     * Получить список всех категорий
     */
    @Transactional
    public List<CategoryDto> index(){                System.out.println(">>cs.i()-B");
        return StreamSupport.stream(cr.findAll().spliterator(), false)
               .map( t -> new CategoryDto(t.getI(),t.getName()) ).collect(Collectors.toList());
    }

}

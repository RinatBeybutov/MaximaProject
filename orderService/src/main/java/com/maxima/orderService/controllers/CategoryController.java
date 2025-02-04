package com.maxima.orderService.controllers;


import com.maxima.orderService.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import com.maxima.orderService.dto.*;


import org.springframework.web.bind.annotation.*;
import com.maxima.orderService.entity.CategoryEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.maxima.orderService.util.*;

import com.maxima.orderService.config.*;

import java.util.UUID;
import static com.maxima.orderService.config.ApiConfig.CATEGORIES;
/**
* Класс Рест Контроллера для реализации API для работы с Категориями
*/
@RestController
@RequestMapping(CATEGORIES)
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    } 

    /**
     * Создать новую категорию
     * @param name название категории
     */
    @GetMapping("/new")
    public ResponseEntity<CategoryDto> create(@RequestParam("uuid") UUID uuid, @RequestParam("name") String name) {
        return ResponseEntity.ok(categoryService.create(new CategoryDto(0L,uuid,name)));
    }

    /**
     * Получить список категорий 
     */
    @GetMapping("/index")
    public ResponseEntity<List<CategoryDto>> index() throws Exception {		System.out.println(">>cc.i()");
        List<CategoryDto> cd=categoryService.index();
        return ResponseEntity.ok(cd);
    }

    /**
     * Получить категорию по UUID
     * @param uuid идентификатор категории
     */
    @GetMapping("/find")
    public ResponseEntity<CategoryDto> find(@RequestParam("uuid") UUID uuid) throws Exception {		System.out.println(">>cc.f()");
        CategoryEntity c=categoryService.find(uuid);
        return ResponseEntity.ok(new CategoryDto(c.getId(),c.getUuid(),c.getName()));
    }

    /**
     * Обновить категорию по UUID
     * @param uuid идентификатор категории
     */
    @PostMapping("/update")
    public void update(@RequestBody CategoryDto cd) throws Exception {	System.out.println(">>cc.u()");
        categoryService.update(cd.getUuid(),cd.getName());
    }

    /**
     * Обновить категорию по UUID
     * @param uuid идентификатор категории
     */
    @GetMapping("/update")
    public void update0(@RequestParam("uuid") UUID uuid,@RequestParam("name") String name) throws Exception {	System.out.println(">>cc.u()");
        categoryService.update(uuid,name);
    }

    /**
     * Удалить категорию по UUID
     * @param uuid идентификатор категории
     */
    @GetMapping("/delete")
    public void delete(@RequestParam("uuid") UUID uuid) throws Exception {	System.out.println(">>cc.d()");
        categoryService.delete(uuid);
    }

    /**
     * Обработчик исключения из метода сервиса для генерации корректного http ответа об ошибке
     */
    @ExceptionHandler
    private ResponseEntity<String> handleException(ResponseException e){
        return new ResponseEntity<>("err", HttpStatus.NOT_FOUND);
    }
}

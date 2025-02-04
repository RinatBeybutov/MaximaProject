package com.maxima.orderService.controllers;


import com.maxima.orderService.service.*;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    /**
     * Создать новую категорию
     * @param categoryDto название категории
     */
    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok(categoryService.create(categoryDto));
    }

    /**
     * Получить список категорий 
     */
    @GetMapping
    public ResponseEntity<List<CategoryDto>> index() throws Exception {
        System.out.println(">>cc.i()");
        List<CategoryDto> cd=categoryService.index();
        return ResponseEntity.ok(cd);
    }

    /**
     * Получить категорию по UUID
     * @param uuid идентификатор категории
     */
    @GetMapping("{uuid}")
    public ResponseEntity<CategoryDto> find(@PathVariable("uuid") UUID uuid) throws Exception {
        System.out.println(">>cc.f()");
        CategoryEntity c=categoryService.find(uuid);
        return ResponseEntity.ok(new CategoryDto(c.getId(),c.getUuid(),c.getName()));
    }

    /**
     * Обновить категорию по UUID
     * @param categoryDto идентификатор категории
     */
    @PutMapping("{uuid}")
    public void update(@PathVariable("uuid") UUID uuid, @RequestBody CategoryDto categoryDto) throws Exception {
        System.out.println(">>cc.u()");
        categoryService.update(uuid,categoryDto);
    }

    /**
     * Удалить категорию по UUID
     * @param uuid идентификатор категории
     */
    @DeleteMapping("{uuid}")
    public void delete(@PathVariable("uuid") UUID uuid) throws Exception {
        System.out.println(">>cc.d()");
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

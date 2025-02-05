package com.maxima.orderService.controllers;

import com.maxima.orderService.service.*;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import com.maxima.orderService.dto.*;

import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.UUID;
import static com.maxima.orderService.config.ApiConfig.CATEGORIES;
/**
* Класс Рест Контроллера для реализации API для работы с Категориями
*/
@RestController
@RequestMapping(CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    /**
     * Создать новую категорию
     * @param categoryCreateDto название категории
     */
    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryCreateDto categoryCreateDto) {
        return ResponseEntity.ok(service.create(categoryCreateDto));
    }

    /**
     * Получить список категорий 
     */
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getList(){
        return ResponseEntity.ok(service.getList());
    }

    /**
     * Получить категорию по UUID
     * @param uuid идентификатор категории
     */
    @GetMapping("/{uuid}")
    public ResponseEntity<CategoryDto> find(@PathVariable("uuid") UUID uuid){
        return ResponseEntity.ok(service.find(uuid));
    }

    /**
     * Обновить категорию по UUID
     * @param categoryCreateDto идентификатор категории
     */
    @PutMapping("/{uuid}")
    public ResponseEntity<Void> update(@PathVariable("uuid") UUID uuid, @RequestBody CategoryCreateDto categoryCreateDto){
        service.update(uuid,categoryCreateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Удалить категорию по UUID
     * @param uuid идентификатор категории
     */
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable("uuid") UUID uuid) {
        service.delete(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

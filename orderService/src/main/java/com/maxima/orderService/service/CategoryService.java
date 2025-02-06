package com.maxima.orderService.service;


import com.maxima.orderService.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.maxima.orderService.entity.*;
import com.maxima.orderService.repo.*;
import com.maxima.orderService.dto.CategoryDto;
import com.maxima.orderService.dto.CategoryCreateDto;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import com.maxima.orderService.exceptions.ResponseException;

import java.util.stream.StreamSupport;

import java.util.UUID;

/**
* Класс Сервиса для реализации работы с Категориями
*/
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper mapper;

    /**
     * Создать категорию
     */
    @Transactional
    public CategoryDto create(CategoryCreateDto dto){
        CategoryEntity categoryEntity=categoryRepository.save(mapper.dtoToEntity(dto));
        return mapper.entityToDto(categoryEntity);
    }

    /**
     * Найти категорию по uuid
     */
    @Transactional
    public CategoryDto find(UUID uuid) throws ResponseException{
        CategoryEntity categoryEntity=categoryRepository.findByUuidRequired(uuid);
        return mapper.entityToDto(categoryEntity);
    }

    /**
     * Обновить категорию по uuid
     */
    @Transactional
    public CategoryDto update(UUID uuid, CategoryCreateDto categoryInputDto) throws ResponseException{
        CategoryEntity categoryEntity=categoryRepository.findByUuidRequired(uuid);
        categoryEntity.setName(categoryInputDto.getName());
        return mapper.entityToDto(categoryRepository.save(categoryEntity));
    }

    /**
     * Удалить категорию по uuid
     */
    @Transactional
    public void delete(UUID uuid) throws ResponseException{
        categoryRepository.findByUuidRequired(uuid);
        categoryRepository.deleteByUuid(uuid);
    }

    /**
     * Получить список всех категорий
     */
    @Transactional
    public List<CategoryDto> getList(){
        return categoryRepository.findAll().stream()
               .map( t -> mapper.entityToDto(t) ).collect(Collectors.toList());
    }

}

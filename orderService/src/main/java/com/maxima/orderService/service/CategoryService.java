package com.maxima.orderService.service;


import com.maxima.orderService.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.maxima.orderService.repo.*;
import com.maxima.orderService.dto.CategoryDto;
import com.maxima.orderService.dto.CategoryCreateDto;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import com.maxima.orderService.exceptions.ResponseException;

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
    public CategoryDto create(CategoryCreateDto dto) {
        var categoryEntity0 = mapper.toEntity(dto);
        var categoryEntity = categoryRepository.save(categoryEntity0);
        return mapper.toDto(categoryEntity);
    }

    /**
     * Найти категорию по uuid
     */
    @Transactional
    public CategoryDto find(UUID uuid) throws ResponseException {
        var categoryEntity = categoryRepository.getByUuid(uuid);
        return mapper.toDto(categoryEntity);
    }

    /**
     * Обновить категорию по uuid
     */
    @Transactional
    public CategoryDto update(UUID uuid, CategoryCreateDto categoryInputDto) throws ResponseException {
        var categoryEntity = categoryRepository.getByUuid(uuid);
        mapper.updateFromDto(categoryInputDto, categoryEntity);
        var categoryEntity1 = categoryRepository.save(categoryEntity);
        return mapper.toDto(categoryEntity1);
    }

    /**
     * Удалить категорию по uuid
     */
    @Transactional
    public void delete(UUID uuid) throws ResponseException {
        categoryRepository.getByUuid(uuid);
        categoryRepository.deleteByUuid(uuid);
    }

    /**
     * Получить список всех категорий
     */
    @Transactional
    public List<CategoryDto> getList() {
        return categoryRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}

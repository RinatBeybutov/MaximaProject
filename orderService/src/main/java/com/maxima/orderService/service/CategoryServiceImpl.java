package com.maxima.orderService.service;

import com.maxima.orderService.CategoryMapper;
import com.maxima.orderService.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    private final CategoryMapper mapper;

    /**
     * Создать категорию
     */
    @Transactional
    public CategoryDto create(CategoryCreateDto dto) {
        var entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    /**
     * Найти категорию по uuid
     */
    @Transactional
    public CategoryDto find(UUID uuid) throws ResponseException {
        var entity = repository.getByUuid(uuid);
        return mapper.toDto(entity);
    }

    /**
     * Обновить категорию по uuid
     */
    @Transactional
    public CategoryDto update(UUID uuid, CategoryCreateDto categoryCreateDto)
            throws ResponseException {
        var entity = repository.getByUuid(uuid);
        mapper.update(categoryCreateDto, entity);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    /**
     * Удалить категорию по uuid
     */
    @Transactional
    public void delete(UUID uuid) throws ResponseException {
        var entity = repository.getByUuid(uuid);
        repository.delete(entity);
    }

    /**
     * Получить список всех категорий
     */
    @Transactional
    public List<CategoryDto> getList() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

}

package com.maxima.orderService.service;


import com.maxima.orderService.CategoryToDtoMapper;
import com.maxima.orderService.CreateDtoToCategoryMapper;
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

    private final CategoryRepository repo;

    private final CategoryToDtoMapper cdm;

    private final CreateDtoToCategoryMapper dcm;

    /**
     * Создать категорию
     */
    @Transactional
    public CategoryDto create(CategoryCreateDto dto){
        return cdm.entityToDto(repo.save(dcm.dtoToEntity(dto)));
    }

    /**
     * Найти категорию по uuid
     */
    @Transactional
    public CategoryDto find(UUID uuid) throws ResponseException{
        return cdm.entityToDto(repo.findByUuid(uuid).orElseThrow( () -> new ResponseException() ));
    }

    /**
     * Обновить категорию по uuid
     */
    @Transactional
    public CategoryDto update(UUID uuid, CategoryCreateDto categoryInputDto) throws ResponseException{                     System.out.println(">>cs.u()-B");
        CategoryEntity c=repo.findByUuid(uuid).orElseThrow( () -> new ResponseException() );
        c.setName(categoryInputDto.getName());
        return cdm.entityToDto(repo.save(c));
    }

    /**
     * Удалить категорию по uuid
     */
    @Transactional
    public void delete(UUID uuid) throws ResponseException{
        if(!repo.existsByUuid(uuid)) throw new ResponseException();
        repo.deleteByUuid(uuid);
    }

    /**
     * Получить список всех категорий
     */
    @Transactional
    public List<CategoryDto> getList(){
        return StreamSupport.stream(repo.findAll().spliterator(), false)
               .map( t -> cdm.entityToDto(t) ).collect(Collectors.toList());
    }

}

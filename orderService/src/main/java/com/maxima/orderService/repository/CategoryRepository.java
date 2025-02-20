package com.maxima.orderService.repository;

import com.maxima.orderService.entity.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

import java.util.UUID;

import com.maxima.orderService.exceptions.ResponseException;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    CategoryEntity save(CategoryEntity c);
    void deleteByUuid(UUID uuid);
    void delete(CategoryEntity entity);
    boolean existsByUuid(UUID uuid);
    List<CategoryEntity> findByName(String name);
    Optional<CategoryEntity> findByUuid(UUID uuid);
    List<CategoryEntity> findAll();

    default CategoryEntity getByUuid(UUID uuid) throws ResponseException{
        return findByUuid(uuid).orElseThrow( () -> new ResponseException() );
    }
}
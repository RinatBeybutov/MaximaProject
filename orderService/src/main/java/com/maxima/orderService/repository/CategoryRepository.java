package com.maxima.orderService.repository;

import com.maxima.orderService.entity.*;

import java.util.List;
import java.util.Optional;

import java.util.UUID;

import com.maxima.orderService.exceptions.ResponseException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity save(CategoryEntity c);
    void delete(CategoryEntity entity);
    Optional<CategoryEntity> findByUuid(UUID uuid);
    List<CategoryEntity> findAll();

    default CategoryEntity getByUuid(UUID uuid) throws ResponseException{
        return findByUuid(uuid).orElseThrow( () -> new ResponseException() );
    }
}
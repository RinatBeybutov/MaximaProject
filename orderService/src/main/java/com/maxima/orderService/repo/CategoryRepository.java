package com.maxima.orderService.repo;

import com.maxima.orderService.model.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category save(Category c);
    void deleteById(long id);
    List<Category> findByName(String name);
    Optional<Category> findById(long id);
    Iterable<Category> findAll();
}
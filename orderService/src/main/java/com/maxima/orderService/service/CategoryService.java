package com.maxima.orderService.service;

import com.maxima.orderService.dto.CategoryDto;
import com.maxima.orderService.dto.CategoryCreateDto;
import java.util.List;
import java.util.UUID;

/**
 * Сервис для реализации работы с Категориями
 */
public interface CategoryService {

  /**
   * Создать категорию
   */
  CategoryDto create(CategoryCreateDto dto);

  /**
   * Найти категорию по uuid
   */
  CategoryDto find(UUID uuid);

  /**
   * Обновить категорию по uuid
   */
  CategoryDto update(UUID uuid, CategoryCreateDto categoryInputDto);

  /**
   * Удалить категорию по uuid
   */
  void delete(UUID uuid);

  /**
   * Получить список всех категорий
   */
  List<CategoryDto> getList();
}


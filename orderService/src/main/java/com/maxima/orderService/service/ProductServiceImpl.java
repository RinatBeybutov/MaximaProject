package com.maxima.orderService.service;

import com.maxima.orderService.dto.ProductCreateDto;
import com.maxima.orderService.dto.ProductViewDto;
import com.maxima.orderService.mapper.ProductMapper;
import com.maxima.orderService.repository.CategoryRepository;
import com.maxima.orderService.repository.ProductRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Класс Сервиса для реализации работы с Продуктами
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

  private final ProductRepository repository;

  private final CategoryRepository categoryRepository;

  private final ProductMapper mapper;

  @Override
  @Transactional
  @CacheEvict(cacheNames = "products")
  public ProductViewDto create(ProductCreateDto dto) {
    log.info("<<creating product:>>");
    var category = categoryRepository.getByUuid(dto.getCategoryUuid());
    var entity = mapper.toEntity(dto);
    entity.setCategory(category);
    entity = repository.save(entity);
    return mapper.toDto(entity);
  }

  @Override
  @Transactional(readOnly = true)
  //@Cacheable(cacheNames = "products")
  public List<ProductViewDto> getList() {
    log.info("<<getting products list:>>");
    return repository.findAll()
        .stream()
        .map(mapper::toDto)
        .toList();
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable(cacheNames = "products", key = "#uuid")
  public ProductViewDto find(UUID uuid) {
    log.info("<<find product:>>");
    var entity = repository.getByUuid(uuid);
    return mapper.toDto(entity);
  }

  @Override
  @Transactional
  @CacheEvict(cacheNames = "products")
  public ProductViewDto update(UUID uuid, ProductCreateDto productCreateDto) {
    log.info("<<update product:>>");
    var entity = repository.getByUuid(uuid);
    var category = categoryRepository.getByUuid(productCreateDto.getCategoryUuid());
    mapper.update(productCreateDto, entity);
    entity.setCategory(category);
    entity = repository.save(entity);
    return mapper.toDto(entity);
  }

  @Override
  @Transactional
  @CacheEvict(cacheNames = "products")
  public void delete(UUID uuid) {
    log.info("<<delete product:>>");
    var entity = repository.getByUuid(uuid);
    repository.delete(entity);
  }
}

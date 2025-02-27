package com.maxima.orderService.service;

import com.maxima.orderService.dto.ProductCreateDto;
import com.maxima.orderService.dto.ProductViewDto;
import java.util.List;
import java.util.UUID;

public interface ProductService {
  ProductViewDto create(ProductCreateDto productCreateDto);
  List<ProductViewDto> getList();
  ProductViewDto find(UUID uuid);
  ProductViewDto update(UUID uuid, ProductCreateDto productCreateDto);
  void delete(UUID uuid);

}

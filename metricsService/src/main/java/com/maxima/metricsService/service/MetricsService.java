package com.maxima.metricsService.service;


import com.maxima.metricsService.dto.OrderMetricsDto;

/**
 * Интерфейс сервиса для работы с метриками заказов.
 */
public interface MetricsService {
  OrderMetricsDto getMetrics();
}

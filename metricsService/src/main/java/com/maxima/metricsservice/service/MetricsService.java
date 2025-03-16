package com.maxima.metricsservice.service;

import com.petProject.MetricsService.dto.OrderMetricsDto;

/**
 * Интерфейс сервиса для работы с метриками заказов.
 */
public interface MetricsService {
  OrderMetricsDto getMetrics();
}

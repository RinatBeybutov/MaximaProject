package com.maxima.metricsservice.repository;

import com.petProject.MetricsService.dto.OrderMetricsDto;

public interface MetricsRedisRepository {

  void incrementTotalOrders();

  OrderMetricsDto getOrderMetrics();

  void clear();
}

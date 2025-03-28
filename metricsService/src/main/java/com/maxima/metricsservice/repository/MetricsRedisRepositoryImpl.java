package com.maxima.metricsservice.repository;

import com.petProject.MetricsService.dto.OrderMetricsDto;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MetricsRedisRepositoryImpl implements MetricsRedisRepository{
  private final RedisTemplate<String, Object> redisTemplate;

  private String getRedisKey() {
    return "order_metrics:" + LocalDate.now();
  }

  @Override
  public void incrementTotalOrders() {
    String key = getRedisKey();
    redisTemplate.opsForHash().increment(key, "totalOrders", 1);
    redisTemplate.expire(key, Duration.ofDays(1));
  }

  @Override
  public OrderMetricsDto getOrderMetrics() {
    String key = getRedisKey();
    Map<Object, Object> entries = redisTemplate.opsForHash().entries(key);
    long totalOrders = Long.parseLong(entries.getOrDefault("totalOrders", "0").toString());

    OrderMetricsDto dto = new OrderMetricsDto();
    dto.setTotalOrders((int) totalOrders);
    return dto;
  }
  @Override
  public void clear() {
    redisTemplate.delete(getRedisKey());
  }
}

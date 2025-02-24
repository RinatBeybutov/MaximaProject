package com.maxima.userService.config;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Исключение, которое выбрасывается в сервисах и обрабатывается глобальным перехватчиком.
 */

@Data
@AllArgsConstructor
public class LocalizedEntityNotFoundException extends RuntimeException {

  private String messageKey;

}

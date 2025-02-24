package com.maxima.userService.config;

import lombok.Getter;

/**
 * Исключение, которое выбрасывается в сервисах и обрабатывается глобальным перехватчиком.
 */


@Getter
public class LocalizedEntityNotFoundException extends RuntimeException {

  private final String messageKey;

  public LocalizedEntityNotFoundException(String messageKey) {
    this.messageKey = messageKey;
  }

}

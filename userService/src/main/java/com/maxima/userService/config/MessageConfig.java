package com.maxima.userService.config;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * Конфигурационный класс для настройки источника сообщений (MessageSource)
 */
@Configuration
public class MessageConfig {

  /**
   * Создает и настраивает бин {@link MessageSource} для работы с локализованными сообщениями.
   */
  @Bean
  public MessageSource messageSource() {
    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    messageSource.setBasename("classpath:messages");
    messageSource.setDefaultEncoding("UTF-8");
    Locale.setDefault(Locale.forLanguageTag("ru"));
    return messageSource;
  }

  /**
   * Создает и настраивает бин {@link LocaleResolver} для определения локали/ Локаль по умолчанию -
   * русский язык.
   */
  @Bean
  public LocaleResolver localeResolver() {
    AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
    resolver.setDefaultLocale(Locale.forLanguageTag("ru"));
    return resolver;
  }

}

package com.maxima.userService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для передачи параметров фильтрации пользователей
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "DTO для фильтрации списка пользователей")
public class UserFilterDto {

  @Schema(description = "Точное имя пользователя", example = "Иван")
  private String nameEquals;

  @Schema(description = "Начало имени пользователя", example = "И")
  private String nameStartsWith;

  @Schema(description = "Точная дата регистрации", example = "2020-01-01")
  private LocalDate registrationDateEquals;

  @Schema(description = "", example = "2020-01-01")
  private LocalDate registrationDateBefore;

  @Schema(description = "", example = "2020-01-01")
  private LocalDate registrationDateAfter;
}

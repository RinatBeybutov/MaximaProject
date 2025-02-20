package com.maxima.userService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Дто сущности пользователя для ввода с фронтэнда
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "DTO для создания пользователя")
public class UserCreateDto {

  @Schema(description = "Имя пользователя", example = "Иван")
  @NotBlank
  private String name;

  @Schema(description = "Email пользователя", example = "user@mail.com")
  @Email
  private String email;
}

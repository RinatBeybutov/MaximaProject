package com.maxima.userService.dto;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserViewDto {

  private UUID uuid;

  private String name;

  private Date registeredAt;

  private String email;
}

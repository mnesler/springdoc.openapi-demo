package com.mnesler.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends User {

  @JsonIgnore
  private static final String UUID_PATTERN =
      "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";

  @NotBlank
  @Builder.Default
  @Pattern(regexp = UUID_PATTERN)
  private String id = UUID.randomUUID().toString();

  @NonNull @Builder.Default @PastOrPresent private ZonedDateTime createdAt = ZonedDateTime.now();

  public static UserEntity from(User user) {
    return UserEntity.builder()
        .username(user.getUsername())
        .name(user.getName())
        .email(user.getEmail())
        .roles(user.getRoles())
        .build();
  }
}

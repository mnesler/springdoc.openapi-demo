package com.mnesler.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

import java.util.Set;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private static final String USERNAME_PATTERN = "^[a-zA-Z0-9_]*$";
  private static final String NAME_PATTERN = "^[A-Z][a-z]*\\s[A-Z][a-z]*$";
  private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";

  @NonNull
  @NotBlank
  @Pattern(regexp = USERNAME_PATTERN)
  @Schema(name = "username", example = "tom_from_myspace", pattern = USERNAME_PATTERN)
  protected String username;

  @NonNull
  @NotBlank
  @Pattern(regexp = NAME_PATTERN)
  @Schema(name = "name", example = "Tom Anderson", pattern = NAME_PATTERN, description = "First Last")
  protected String name;

  @NonNull
  @NotBlank
  @Pattern(regexp = EMAIL_PATTERN)
  @Schema(name = "email", example = "tom@myspace.com", pattern = EMAIL_PATTERN)
  protected String email;

  @NonNull
  @NotEmpty
  @Singular
  @ArraySchema(schema = @Schema(implementation = UserRole.class), uniqueItems = true, minItems = 1, maxItems = 5)
  protected Set<UserRole> roles;

}

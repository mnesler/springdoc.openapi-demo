package com.mnesler.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

@Schema(name = "roles", example = "[ADMIN]")
public enum UserRole {
  ADMIN,
  EMPLOYEE,
  CUSTOMER,
  DEVELOPER,
  GUEST
}

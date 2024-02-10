package com.mnesler.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HelloResponse {
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String message;
}

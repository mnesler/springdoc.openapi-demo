package com.mnesler.service;

import com.mnesler.controller.HelloResponse;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

  public HelloResponse getMessage(String username) {
    return HelloResponse.builder().message("Hello, " + username + "!").build();
  }
}

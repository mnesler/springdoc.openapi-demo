package com.mnesler.controller;

import com.mnesler.service.HelloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(onConstructor_ = @Autowired)
public class HelloController {

  private static final Logger log = LoggerFactory.getLogger(HelloController.class);

  private final HelloService helloService;

  @Operation(summary = "Get a hello message")
  @RequestMapping(value = "/{username}", method = RequestMethod.GET)
  @ApiResponse(
      responseCode = "200",
      description = "Hello message",
      content = @Content(schema = @Schema(implementation = HelloResponse.class)))
  public HelloResponse getHello(@PathVariable("username") String username) {
    log.info("Getting hello message for {}", username);
    return helloService.getMessage(username);
  }
}

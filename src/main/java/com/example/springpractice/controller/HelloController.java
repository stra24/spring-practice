package com.example.springpractice.controller;

import com.example.springpractice.service.HelloService;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

  private final HelloService helloService;

  @Autowired
  public HelloController(HelloService helloService) {
    this.helloService = helloService;
    logger.info("HelloController の Bean を作成してDIコンテナの中に入れます");
  }

  @GetMapping("/api/hello")
  public Map<String, String> hello() {
    String greetMessage = helloService.greet("太郎");
    return Map.of("greetMessage", greetMessage);
  }
}

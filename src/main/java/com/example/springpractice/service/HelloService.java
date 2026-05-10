package com.example.springpractice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

  private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

  public HelloService() {
    logger.info("HelloService の Bean を作成してDIコンテナの中に入れます");
  }

  public String greet(String name) {
    return "Hello, " + name + "!";
  }
}
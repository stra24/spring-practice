package com.example.springpractice.config;

import java.time.Clock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

  private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

  @Bean
  public Clock systemClock() {
    logger.info("Clock の Bean を作成してDIコンテナの中に入れます");
    return Clock.systemDefaultZone();
  }
}

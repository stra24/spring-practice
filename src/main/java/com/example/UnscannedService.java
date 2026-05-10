package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UnscannedService {

  private static final Logger log = LoggerFactory.getLogger(UnscannedService.class);

  public UnscannedService() {
    log.info("UnscannedService の Bean を作成してDIコンテナの中に入れます");
  }
}
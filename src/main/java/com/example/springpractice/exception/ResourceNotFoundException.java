package com.example.springpractice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String resourceName, String id) {
    super(resourceName + " が見つかりませんでした。id = " + id);
  }
}

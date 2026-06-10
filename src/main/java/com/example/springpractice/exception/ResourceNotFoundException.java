package com.example.springpractice.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String resourceName, String id) {
    super(resourceName + " が見つかりませんでした。id = " + id);
  }
}

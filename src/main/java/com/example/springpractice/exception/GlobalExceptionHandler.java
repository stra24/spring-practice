package com.example.springpractice.exception;

import com.example.springpractice.dto.ErrorResponse;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    Map<String, String> fieldErrors = new LinkedHashMap<>();
    ex.getBindingResult().getFieldErrors()
        .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));
    return new ErrorResponse("入力値が不正です", fieldErrors);
  }

  @ExceptionHandler(HandlerMethodValidationException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleHandlerMethodValidation(HandlerMethodValidationException ex) {
    Map<String, String> paramErrors = new LinkedHashMap<>();
    ex.getParameterValidationResults().forEach(result -> {
      String paramName = result.getMethodParameter().getParameterName();
      String message = result.getResolvableErrors().stream()
          .map(MessageSourceResolvable::getDefaultMessage)
          .collect(Collectors.joining(", "));
      paramErrors.put(paramName != null ? paramName : "param", message);
    });
    return new ErrorResponse("入力値が不正です", paramErrors);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleResourceNotFound(ResourceNotFoundException ex) {
    return new ErrorResponse(ex.getMessage(), null);
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleException(Exception ex) {
    return new ErrorResponse("サーバーエラーが発生しました", null);
  }
}

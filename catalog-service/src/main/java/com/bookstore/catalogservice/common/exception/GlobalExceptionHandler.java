package com.bookstore.catalogservice.common.exception;

import com.bookstore.catalogservice.common.response.CommonResult;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.UUID;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(RunTimeExceptionPlaceHolder.class)
  public CommonResult<ErrorResponse> handleCustomException(RunTimeExceptionPlaceHolder ex) {

    ErrorResponse errorResponse = populateErrorResponse("400", ex.getMessage());
    log.error("Something went wrong, Exception : " + ex.getMessage());
    ex.printStackTrace();
    return CommonResult.badRequest(errorResponse);

  }

  @ExceptionHandler(InvalidFormatException.class)
  public CommonResult<ErrorResponse> handleCustomException(InvalidFormatException ex) {

    ErrorResponse errorResponse = populateErrorResponse("400", ex.getMessage());
    log.error("Something went wrong, Exception : " + ex.getMessage());
    ex.printStackTrace();
    return CommonResult.badRequest(errorResponse);

  }

  @ExceptionHandler(Exception.class)
  public CommonResult<ErrorResponse> handleCustomException(Exception ex) {

    ErrorResponse errorResponse = populateErrorResponse("500",
            ex.getMessage());
    log.error("Something went wrong, Exception : " + ex.getMessage());
    ex.printStackTrace();
    return CommonResult.failed();

  }

  @ExceptionHandler(AccessDeniedException.class)
  public CommonResult<ErrorResponse> handleCustomException(AccessDeniedException ex) {

    ErrorResponse errorResponse = populateErrorResponse("401",
            ex.getMessage());
    log.error("Something went wrong, Exception : " + ex.getMessage());
    ex.printStackTrace();
    return CommonResult.unauthorized(null);

  }

  public ErrorResponse populateErrorResponse(String code, String message) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setUuid(UUID.randomUUID());

    Error error = new Error();
    error.setCode(code);
    error.setMessage(message);

    errorResponse.setErrors(Collections.singletonList(error));

    return errorResponse;
  }
}
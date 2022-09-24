package com.bookstore.apigatewayservice.exceptions;

public class RunTimeExceptionPlaceHolder extends RuntimeException {

  int httpStatus;

  public RunTimeExceptionPlaceHolder(String message, int httpStatus) {
    super(message);
    this.httpStatus = httpStatus;
  }

  public int getHttpStatus() {
    return httpStatus;
  }

}
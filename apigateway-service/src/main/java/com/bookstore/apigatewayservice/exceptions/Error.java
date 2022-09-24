package com.bookstore.apigatewayservice.exceptions;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Phuc Tran
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Error {

  private String code;
  private String message;
}
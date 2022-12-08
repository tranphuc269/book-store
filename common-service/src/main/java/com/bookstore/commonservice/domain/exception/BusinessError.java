package com.bookstore.commonservice.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Data
public class BusinessError implements Serializable {
    private static final long serialVersionUID = 2405172041950251807L;
    private int code;
    private String message;
    private HttpStatus httpStatus;
}

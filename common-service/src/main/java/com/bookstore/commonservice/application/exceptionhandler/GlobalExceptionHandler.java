package com.bookstore.commonservice.application.exceptionhandler;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.CaseFormat;
import com.bookstore.commonservice.application.response.dto.BaseResponse;
import com.bookstore.commonservice.application.response.dto.FieldViolation;
import com.bookstore.commonservice.domain.exception.BusinessError;
import com.bookstore.commonservice.domain.exception.ErrorCommon;
import com.bookstore.commonservice.domain.exception.ExceptionCommon;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@ControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private ObjectMapper objectMapper;

    @ExceptionHandler(ExceptionCommon.class)
    public ResponseEntity<BaseResponse<Void>> handlerBusinessException(ExceptionCommon exception){
        BaseResponse<Void> data = BaseResponse.ofFailed(exception);
        return new ResponseEntity<>(data, exception.getErrorCode().getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<FieldViolation> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(e -> new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getField()), e.getDefaultMessage()))
                .collect(Collectors.toList());
        BusinessError errorCode = ErrorCommon.INVALID_PARAMETERS;
        BaseResponse<Void> data = BaseResponse.ofFailed(errorCode, "Invalid parameters of object: " + exception.getBindingResult().getObjectName(), errors);
        HttpStatus status = errorCode.getHttpStatus();
        return new ResponseEntity<>(data, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception exception, HttpServletRequest request) {
        BusinessError errorCode = ErrorCommon.INTERNAL_SERVER_ERROR;
        BaseResponse<Void> data = BaseResponse.ofFailed(errorCode, exception.getMessage());
        HttpStatus status = errorCode.getHttpStatus();
        return new ResponseEntity<>(data, status);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Void>> handleIllegalArgumentException(IllegalArgumentException exception, HttpServletRequest request) {
        BusinessError errorCode = ErrorCommon.INVALID_PARAMETERS;
        BaseResponse<Void> data = BaseResponse.ofFailed(errorCode, exception.getMessage());
        HttpStatus status = errorCode.getHttpStatus();
        return new ResponseEntity<>(data, status);
    }

}

package com.bookstore.catalogservice.common.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

public class CustomResponse<T> extends ResponseEntity<T> implements Serializable {

    private static final long serialVersionUID = 7156526077883281625L;

    public CustomResponse(HttpStatus status) {
        super(status);
    }

    public CustomResponse(T body, HttpStatus status) {
        super(body, status);
    }

    public CustomResponse(MultiValueMap<String, String> headers, HttpStatus status) {
        super(headers, status);
    }

    public CustomResponse(T body, MultiValueMap<String, String> headers, HttpStatus status) {
        super(body, headers, status);
    }

    public CustomResponse(T body, MultiValueMap<String, String> headers, int rawStatus) {
        super(body, headers, rawStatus);
    }
}

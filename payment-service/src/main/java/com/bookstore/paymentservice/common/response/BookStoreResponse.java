package com.bookstore.paymentservice.common.response;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookStoreResponse {
    String message;
    Boolean success;
    Object data;
}

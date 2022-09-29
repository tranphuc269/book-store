package com.bookstore.catalogservice.vo.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateOrUpdateProducerRequest {
    @NotNull(message = "Producer name should not be null!")
    @NotEmpty(message = "Producer name should not be empty!")
    private String producerName;
    private String producerId;
    private String description;
    private String imgUrl;
}

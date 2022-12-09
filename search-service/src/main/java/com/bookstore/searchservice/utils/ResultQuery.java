package com.bookstore.searchservice.utils;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
public class ResultQuery {

    private Float timeTook;
    private Integer numberOfResults;
    List<ProductQuery> products;

    public ResultQuery() {
    }

    public ResultQuery(Float timeTook, Integer numberOfResults, List<ProductQuery> products) {
        this.timeTook = timeTook;
        this.numberOfResults = numberOfResults;
        this.products = products;
    }

    public Float getTimeTook() {
        return timeTook;
    }

    public void setTimeTook(Float timeTook) {
        this.timeTook = timeTook;
    }

    public Integer getNumberOfResults() {
        return numberOfResults;
    }

    public void setNumberOfResults(Integer numberOfResults) {
        this.numberOfResults = numberOfResults;
    }

}
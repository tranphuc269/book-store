package com.bookstore.searchservice.service;

import com.bookstore.searchservice.domain.dto.ProductDTO;
import com.bookstore.searchservice.utils.ResultQuery;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface ISearchService {
    List<ProductDTO> searchFromQuery(String query) throws IOException, JSONException;
}
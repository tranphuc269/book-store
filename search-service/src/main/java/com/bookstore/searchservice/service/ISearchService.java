package com.bookstore.searchservice.service;

import com.bookstore.searchservice.utils.ResultQuery;
import org.json.JSONException;

import java.io.IOException;

public interface ISearchService {
    ResultQuery searchFromQuery(String query) throws IOException, JSONException;
}
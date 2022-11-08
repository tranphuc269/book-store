package com.bookstore.searchservice.controller;

import com.bookstore.searchservice.service.ISearchService;
import com.bookstore.searchservice.utils.Constants;
import com.bookstore.searchservice.utils.PathResources;
import com.bookstore.searchservice.utils.ResultQuery;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(PathResources.SEARCH_CONTROLLER)
public class SearchController {

    private ISearchService searchService;

    @Autowired
    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(Constants.SEARCH_QUERY + "/{" + Constants.QUERY + "}")
    public ResponseEntity<ResultQuery> searchQuery(@PathVariable String query) throws IOException, JSONException {
        return new ResponseEntity<> (searchService.searchFromQuery(query.trim().toLowerCase()), HttpStatus.OK);
    }
}
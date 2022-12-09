package com.bookstore.searchservice.service.impl;

import com.bookstore.searchservice.domain.dto.ProductDTO;
import com.bookstore.searchservice.repository.IProductEsRepository;
import com.bookstore.searchservice.service.ISearchService;
import com.bookstore.searchservice.utils.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.DataInput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SearchService implements ISearchService {


    @Value("${api.elasticsearch.uri}")
    private String elasticSearchUri;

    @Value("${api.elasticsearch.search}")
    private String elasticSearchSearchPrefix;

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    @Override
    public List<ProductDTO> searchFromQuery(String query) throws IOException, JSONException {
        String body = HelperFunctions.buildMultiIndexMatchBody(query);
        ResultQuery resultQuery = executeHttpRequest(body);
        List<ProductDTO> productDTOS = new ArrayList<>();
        resultQuery.getProducts().forEach(productQuery -> {
            productDTOS.add(ProductDTO
                    .builder()
                    .availableItemCount(productQuery.get_source().getAvailableItemCount())
                    .categoryId(productQuery.get_source().getCategoryId())
                    .images(productQuery.get_source().getImages())
                    .price(productQuery.get_source().getPrice())
                    .producerId(productQuery.get_source().getProducerId())
                    .productName(productQuery.get_source().getProductName())
                    .productDescription(productQuery.get_source().getProductDescription())
                    .productId(productQuery.get_source().getProductId())
                    .build());
        });
        return productDTOS;
    }

    /**
     * Fetch resultQuery from elastic engine for the given body
     *
     * @param body String
     * @return ResultQuery
     * @throws IOException IOException
     */
    private ResultQuery executeHttpRequest(String body) throws IOException, JSONException {
        Gson gson = new Gson();
        List<ProductQuery> productList = new ArrayList<>();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ResultQuery resultQuery = new ResultQuery();
            HttpPost httpPost = new HttpPost(HelperFunctions.buildSearchUri(elasticSearchUri
                    , "", elasticSearchSearchPrefix));
            httpPost.setHeader(Constants.CONTENT_ACCEPT, Constants.APP_TYPE);
            httpPost.setHeader(Constants.CONTENT_TYPE, Constants.APP_TYPE);
            httpPost.setEntity(new StringEntity(body, Constants.ENCODING_UTF8));
            HttpResponse response = httpClient.execute(httpPost);
            String message = EntityUtils.toString(response.getEntity());
            JSONObject myObject = new JSONObject(message);
            if (myObject.getJSONObject(Constants.HITS) != null) {
//                System.out.println("myObject.getJSONObject(Constants.HITS).getJSONArray(Constants.HITS) : " + myObject.getJSONObject(Constants.HITS).getJSONArray(Constants.HITS));;
                productList = JsonMapper
                        .readList(myObject.getJSONObject(Constants.HITS).getJSONArray(Constants.HITS).toString(),
                                ProductQuery.class);
                resultQuery
                        .setProducts(productList);
                resultQuery
                        .setNumberOfResults(myObject.getJSONObject(Constants.HITS).getJSONObject(Constants.TOTAL_HITS)
                                .getInt(Constants.RESULT_VALUE));
                resultQuery.setTimeTook((float) ((double) myObject.getInt(Constants.TOOK) / Constants.TO_MS));
            } else {
                resultQuery.setProducts(new ArrayList<>());
                resultQuery.setNumberOfResults(0);
                resultQuery.setTimeTook((float) ((double) myObject.getInt(Constants.TOOK) / Constants.TO_MS));
            }

            return resultQuery;
        }
    }
}
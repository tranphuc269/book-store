package com.bookstore.catalogservice.controller;

import com.bookstore.catalogservice.common.response.CommonResult;
import com.bookstore.catalogservice.common.util.CommonUtilityMethods;
import com.bookstore.catalogservice.kafka.BookStoreKafkaProducer;
import com.bookstore.catalogservice.service.ProductService;
import com.bookstore.catalogservice.service.S3BucketStorageService;
import com.bookstore.catalogservice.utils.StringConstant;
import com.bookstore.catalogservice.vo.request.CreateCartItem;
import com.bookstore.catalogservice.vo.request.CreateProductRequest;
import com.bookstore.catalogservice.vo.resonse.ProductResponse;
import com.bookstore.catalogservice.vo.resonse.UpdateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
public class ProductController {
    @Autowired
    private ProductService productService;


    @Autowired
    private BookStoreKafkaProducer kafkaProducer;

    @Autowired
    private S3BucketStorageService s3BucketStorageService;

    @PostMapping("/product")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public CommonResult<String> createProduct(@RequestParam(name = "productName") String productName,
                                              @RequestParam(name = "description") String description,
                                              @RequestParam(name = "price") double price,
                                              @RequestParam(name = "categoryId") String categoryId,
                                              @RequestParam(name = "producerId") String producerId,
                                              @RequestParam(name = "availableItemCount") int availableItemCount,
                                              @RequestParam(name = "files") MultipartFile[] files,
                                              @RequestParam(name = "author") String author,
                                              @RequestParam(name = "dimension") String dimension,
                                              @RequestParam(name = "weight") String weight,
                                              @RequestParam(name = "pageCount") int pageCount


    ) {
        CreateProductRequest
                createProductRequest = CreateProductRequest
                .builder()
                .productName(productName)
                .image(new ArrayList<>())
                .availableItemCount(availableItemCount)
                .description(description)
                .price(price)
                .categoryId(categoryId)
                .producerId(producerId)
                .author(author)
                .dimension(dimension)
                .pageCount(pageCount)
                .weight(weight)
                .build();
        for (MultipartFile file : files) {
            String imgUrl = s3BucketStorageService.uploadFileToS3(file);
            createProductRequest.getImage().add(imgUrl);
        }
        String product = productService.createProduct(createProductRequest);

        return CommonResult.success("ok");
    }

    @GetMapping("/product/{productId}")
    @Cacheable(value = "/product", key = "#productId")
    public CommonResult<ProductResponse> getProduct(@PathVariable("productId") String productId) {

        ProductResponse product = productService.getProduct(productId);

        return CommonResult.success(product);
    }

    @DeleteMapping("/product/{productId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public CommonResult<String> deleteProductCategory(@PathVariable("productId") String productId) {

        productService.deleteProduct(productId);

        return CommonResult.success("ok");
    }

    @PutMapping("/product")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public CommonResult<String> updateProduct(@RequestBody @Valid UpdateProductRequest updateProductRequest) {

        productService.updateProduct(updateProductRequest);

        return CommonResult.success("ok");
    }


    @GetMapping(value = "/products", produces = "application/json")
    @Cacheable(value = "products?", key = "#sort+#page+#size")
    public CommonResult<List<ProductResponse>> getAllProducts(@RequestParam(value = "sort", required = false) String sort,
                                                              @RequestParam(value = "page", required = false) Integer page,
                                                              @RequestParam(value = "size", required = false) Integer size) {

        Page<ProductResponse> list = productService.getAllProducts(sort, page, size);

        List<ProductResponse> productResponses = new ArrayList<>();
        list.forEach(productResponses::add);

        return CommonResult.success(productResponses);

    }


    @GetMapping(value = "/products/product", produces = "application/json")
    @Cacheable(value = "producerId", key = "#producerId")
    public CommonResult<List<ProductResponse>> getProductByProducerId(
            @RequestParam(value = "producerId", required = false) String producerId) {
        List<ProductResponse> productResponses = productService.getProductByProducerId(producerId);
        return CommonResult.success(productResponses);
    }

    @PostMapping(value = "/add-to-cart")
    public CommonResult<String> addProductToCart(@RequestBody @Valid CreateCartItem createCartItem) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = CommonUtilityMethods.getUserIdFromToken(authentication);
        createCartItem.setUserId(userId);
        kafkaProducer.send(StringConstant.ADD_PRODUCT_TO_CART_TOPIC, createCartItem);
        return CommonResult.success("ok");
    }
}
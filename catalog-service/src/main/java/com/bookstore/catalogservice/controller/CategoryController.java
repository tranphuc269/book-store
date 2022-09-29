package com.bookstore.catalogservice.controller;

import com.bookstore.catalogservice.dao.CategoryDAO;
import com.bookstore.catalogservice.service.CategoryService;
import com.bookstore.catalogservice.service.S3BucketStorageService;
import com.bookstore.catalogservice.vo.request.CreateCategoryRequest;
import com.bookstore.catalogservice.vo.request.UpdateCategoryRequest;
import com.bookstore.catalogservice.vo.resonse.ProductCategoriesPagedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private S3BucketStorageService s3BucketStorageService;

    @PostMapping("/category")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> createCategory(@RequestParam(name = "categoryName") String categoryName,
                                            @RequestParam(name = "description") String description,
                                            @RequestParam(name = "file") MultipartFile file
                                                   ) {
        String imgUrl = s3BucketStorageService.uploadFileToS3(file);
        CreateCategoryRequest createCategoryRequest = CreateCategoryRequest
                .builder()
                .categoryName(categoryName)
                .description(description)
                .imgUrl(imgUrl)
                .build();

        String category = categoryService.createCategory(createCategoryRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{categoryId}")
                .buildAndExpand(category).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDAO> getCategory(@PathVariable("categoryId") String categoryId) {

        CategoryDAO categoryDAO = categoryService.getCategory(categoryId);

        return ResponseEntity.ok(categoryDAO);
    }

    @DeleteMapping("/category/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") String categoryId) {

        categoryService.deleteCategory(categoryId);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/category")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<?> updateCategory(@RequestBody @Valid UpdateCategoryRequest updateCategoryRequest) {

        categoryService.updateCategory(updateCategoryRequest);

        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/categories", produces = "application/json")
    public ResponseEntity<?> getAllProductCategories(@RequestParam(value = "sort", required = false) String sort,
                                                     @RequestParam(value = "page", required = false) Integer page,
                                                     @RequestParam(value = "size", required = false) Integer size,
                                                     PagedResourcesAssembler<CategoryDAO> assembler) {
    
        Page<CategoryDAO> list = categoryService.getCategories(sort, page, size);
    
        Link link = Link.of(ServletUriComponentsBuilder.fromCurrentRequest()
                                                        .build()
                                                        .toUriString());

        PagedModel<EntityModel<CategoryDAO>> resource = assembler.toModel(list, link);
    
        ProductCategoriesPagedResponse productCategoriesPagedResponse = new ProductCategoriesPagedResponse();
        productCategoriesPagedResponse.setData(list);

        if (resource.getLink("first").isPresent()) {
            productCategoriesPagedResponse.get_links().put("first", resource.getLink("first").get().getHref());
        }

        if (resource.getLink("prev").isPresent()) {
            productCategoriesPagedResponse.get_links().put("prev", resource.getLink("prev").get().getHref());
        }

        if (resource.getLink("self").isPresent()) {
            productCategoriesPagedResponse.get_links().put("self", resource.getLink("self").get().getHref());
        }

        if (resource.getLink("next").isPresent()) {
            productCategoriesPagedResponse.get_links().put("next", resource.getLink("next").get().getHref());
        }

        if (resource.getLink("last").isPresent()) {
            productCategoriesPagedResponse.get_links().put("last", resource.getLink("last").get().getHref());
        }
    
        return ResponseEntity.ok(productCategoriesPagedResponse);

    }
}
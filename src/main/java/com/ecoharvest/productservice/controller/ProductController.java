package com.ecoharvest.productservice.controller;

import com.ecoharvest.productservice.dto.ProductRequest;
import com.ecoharvest.productservice.dto.ProductResponse;
import com.ecoharvest.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest)  {
        productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts()  {
        return  productService.getAllProducts();
    }

    @GetMapping("/{product-id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProduct(@PathVariable("product-id") String id)  {
        return productService.getProductById(id);
    }

    @PutMapping("/{product-id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable("product-id") String id, @RequestBody ProductRequest productRequest)
    {
        productService.updateProduct(id, productRequest);
    }

    @DeleteMapping(("/{product-id}"))
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("product-id") String id)  {
        productService.deleteProduct(id);
    }
}

package com.ecoharvest.productservice.service;

import com.ecoharvest.productservice.dto.ProductRequest;
import com.ecoharvest.productservice.dto.ProductResponse;
import com.ecoharvest.productservice.model.Product;
import com.ecoharvest.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;

    //create new product
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is created successfully", product.getId());
    }

    //get whole product catalogue
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToproductResponse).toList();
    }

    //get specific product
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id).get();

        return mapToproductResponse(product);
    }

    private ProductResponse mapToproductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    //update product details
    public void updateProduct(String id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).get();
        product.setProductName(productRequest.getProductName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);
        log.info("Product {} is updated successfully", id);
    }

    //delete specific product
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
        log.info("Product {} is deleted successfully", id);
    }
}

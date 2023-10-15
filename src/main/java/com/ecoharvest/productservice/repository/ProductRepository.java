package com.ecoharvest.productservice.repository;

import com.ecoharvest.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}

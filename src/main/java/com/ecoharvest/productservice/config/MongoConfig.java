package com.ecoharvest.productservice.config;

import com.ecoharvest.productservice.model.Product;
import com.ecoharvest.productservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories (basePackageClasses = ProductRepository.class)
@Configuration
public class MongoConfig {
}

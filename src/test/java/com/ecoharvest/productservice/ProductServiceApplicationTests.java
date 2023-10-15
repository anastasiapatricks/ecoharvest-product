package com.ecoharvest.productservice;

import com.ecoharvest.productservice.dto.ProductRequest;
import com.ecoharvest.productservice.dto.ProductResponse;
import com.ecoharvest.productservice.model.Product;
import com.ecoharvest.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	//using docker mongo image to test
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;	//convert object to json and vice versa
	@Autowired
	private ProductRepository productRepository;

	//add uri to config after container is started
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry)  {
		dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.productName("iPhone 13")
				.description("iPhone 13")
				.price(BigDecimal.valueOf(1200))
				.build();
	}


	/* Test: create new product */
	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, productRepository.findAll().size());  //assertTrue(productRepository.findAll().size() == 1)
	}

	/* Test: Get All products */
	@Test
	void shouldGetAllProducts() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productRequestString))
				.andExpect(status().isOk());
	}

	/* TODO: Test: Get a specific product */

	/* TODO: Test: Update a specific product */

	/* TODO: Test: Delete a specific product */
}

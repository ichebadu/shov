package com.iche.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iche.product.dto.request.ProductRequest;
import com.iche.product.dto.response.ApiResponse;
import com.iche.product.dto.response.ProductResponse;
import com.iche.product.repository.ProductRepository;
import org.junit.Assert;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductApplicationTests {
    @Autowired
    private MockMvc mockMvc;


    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ProductRepository productRepository;

    // Set the container to be used, added static to get the MongoDB container
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

    // Dynamically set the URI (mongoDBContainer::getReplicaSetUrl) when creating the test context
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest = getProductRequest();
        String productRequestString = objectMapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/product/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(MockMvcResultMatchers.status().isCreated());
        Assertions.assertTrue(productRepository.findAll().size() == 1);
    }

    private ProductRequest getProductRequest() {
        return ProductRequest.builder()
                .name("Iphone 13")
                .description("Iphone")
                .price(BigDecimal.valueOf(234))
                .build();
    }

    @Test
    void shouldViewProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product/all-products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(result -> {
                    // Deserialize the response JSON into a list of ProductResponse object
                    String jsonResponse = result.getResponse().getContentAsString();
                    ApiResponse<List<ProductResponse>> apiResponse = objectMapper.readValue(jsonResponse, new TypeReference<ApiResponse<List<ProductResponse>>>() {});

                    //Assert that the response contains at least one product
                    Assertions.assertEquals("api received", apiResponse.getMessage());

                    List<ProductResponse> productResponses = apiResponse.getPayload();
                    Assertions.assertTrue(productResponses.isEmpty());
                });
    }
}

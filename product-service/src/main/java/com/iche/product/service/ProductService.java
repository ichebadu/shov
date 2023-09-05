package com.iche.product.service;

import com.iche.product.dto.request.ProductRequest;
import com.iche.product.dto.response.ProductResponse;
import com.iche.product.model.Product;
import com.iche.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository productRepository;


    public String createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved" ,product.getId());
        return "product added successfully";
    }

    public List<ProductResponse> getAllProducts(){
        List <Product> products = productRepository.findAll();

        return products.stream().map(
                product -> mapToProductResponse(product)).toList();

    }

    private ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

}

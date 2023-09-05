package com.iche.product.controller;

import com.iche.product.dto.request.ProductRequest;
import com.iche.product.dto.response.ApiResponse;
import com.iche.product.dto.response.ProductResponse;
import com.iche.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<String>> createProduct(@RequestBody ProductRequest productRequest){
        ApiResponse<String> apiResponse = new ApiResponse<>(productService.createProduct(productRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all-products")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts(){
        ApiResponse<List<ProductResponse>> apiResponse = new ApiResponse<>(productService.getAllProducts());
        System.out.println(">>>>>>>>>>>>>>{}>>>>>>>>>>>>>>"+ apiResponse);
        return ResponseEntity.ok().body(apiResponse);
    }
}

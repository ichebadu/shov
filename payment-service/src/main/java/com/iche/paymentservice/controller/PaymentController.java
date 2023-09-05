package com.iche.paymentservice.controller;

import com.iche.paymentservice.dto.response.ApiResponse;
import com.iche.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

//    @PostMapping()
//    public ResponseEntity<ApiResponse<String>> webhook(){
//        ApiResponse<String> apiResponse = new ApiResponse<>();
//        return new ResponseEntity<>();
//    }

}

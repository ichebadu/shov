package com.iche.order.controller;

import com.iche.order.dto.request.OrderRequest;
import com.iche.order.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/create-order")
    @ResponseStatus(HttpStatus.CREATED)
    //    @TimeLimiter(name="inventory-service")
//  @Retry(name= "inventory-service")
    @CircuitBreaker(name = "inventory-service", fallbackMethod = "fallbackMethod")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        return orderService.placeOrder(orderRequest);
    }

//    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest){
//
//        return CompletableFuture.supplyAsync(()->orderService.placeOrder(orderRequest));
////        orderService.createOrder(orderRequest);
////        return "order placed successfully";
//    }


//    public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
//        return CompletableFuture.supplyAsync(()-> "Oop Something went wrong, please order after some time");
//
//    }
    public String fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
        return "Oop Something went wrong, please order after some time";
    }
}

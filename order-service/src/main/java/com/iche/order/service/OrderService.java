package com.iche.order.service;

import com.iche.order.dto.request.OrderRequest;
import com.iche.order.dto.response.OrderResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface OrderService {

    String createOrder(OrderRequest orderRequest);
}

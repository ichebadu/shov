package com.iche.order.service;

import com.iche.order.dto.request.OrderRequest;


public interface OrderService {

    String placeOrder(OrderRequest orderRequest);
}

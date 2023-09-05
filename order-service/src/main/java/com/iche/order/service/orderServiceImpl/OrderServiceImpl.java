package com.iche.order.service.orderServiceImpl;

import com.iche.order.dto.request.OrderLineItemsDto;
import com.iche.order.dto.request.OrderRequest;
import com.iche.order.dto.response.InventoryResponse;
import com.iche.order.exception.ProductNotInStock;
import com.iche.order.model.Order;
import com.iche.order.model.OrderLineItems;
import com.iche.order.repository.OrderRepository;
import com.iche.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final WebClient.Builder webClientBuilder;
    private final OrderRepository orderRepository;

    @Override
    public String createOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        System.out.println(order);

//        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
//                .stream()
//                .map(getOrderListItem -> OrderLineItemsListMapToDTO(getOrderListItem)
//                ).toList();

                List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::OrderLineItemsListMapToDTO)
                        .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream()
                .map(orderLineItem -> orderLineItem.getSkuCode()).toList();


        //calls tell order-service and place order if product is in stock
        InventoryResponse[] inventoryResponsesArray = webClientBuilder.build().get()
                        .uri("http://inventory-service/api/v1/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                                .retrieve()
                                        .bodyToMono(InventoryResponse[].class)
                                                .block();

        boolean allProductsInStock=Arrays.stream(inventoryResponsesArray).allMatch(InventoryResponse::isInStock);
        if(allProductsInStock){
            orderRepository.save(order);
        }else{
            throw new ProductNotInStock("product is not in stuck please try again later");
        }

        System.out.println("<>>>>>>>>>>>>>>>>>>>>>{}>>>>>>>>>>>>>>>>>>"+ order);
        return "processed";
//
//                OrderResponse.builder()
//                .orderLineItemsDtoList(orderRequest.getOrderLineItemsDtoList())
//                .build();

    }
    public OrderLineItems OrderLineItemsListMapToDTO(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}

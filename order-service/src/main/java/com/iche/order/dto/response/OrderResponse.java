package com.iche.order.dto.response;

import com.iche.order.dto.request.OrderLineItemsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private List<OrderLineItemsDto> orderLineItemsDtoList;
}

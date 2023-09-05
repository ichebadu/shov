package com.iche.inventoryservice.service;

import com.iche.inventoryservice.dto.response.InventoryResponse;
import com.iche.inventoryservice.model.Inventory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface InventoryService {

    List<InventoryResponse> isInStock(List<String> skuCode);
}

package com.iche.inventoryservice.service.serviceImpl;

import com.iche.inventoryservice.dto.response.InventoryResponse;
import com.iche.inventoryservice.repository.InventoryRepository;
import com.iche.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(inventory ->
                        InventoryResponse.builder()
                                .skuCode(inventory.getSkuCode())
                                .isInStock(inventory.getQuantity() > 0)
                                .build())
                .toList();
    }
}

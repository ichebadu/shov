package com.iche.inventoryservice;

import com.iche.inventoryservice.model.Inventory;
import com.iche.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository){
        return arg -> {
            Inventory inventory = new Inventory();
                    inventory.setSkuCode("iphone_13");
                    inventory.setQuantity(0);

                    Inventory inventory1 = new Inventory();
                            inventory1.setSkuCode("iphone");
                            inventory1.setQuantity(20);

                            inventoryRepository.save(inventory);
                            inventoryRepository.save(inventory1);
        };

    }

}

package com.iche.product.productConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class config {
    @Bean
    public static ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

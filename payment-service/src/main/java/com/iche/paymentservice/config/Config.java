package com.iche.paymentservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iche.paymentservice.utils.PaystackUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;


@Configuration
public class Config {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders(){
        return new HttpHeaders();
    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
    @Bean
    public PaystackUtil paystackUtil(){
        return new PaystackUtil();
    }


}



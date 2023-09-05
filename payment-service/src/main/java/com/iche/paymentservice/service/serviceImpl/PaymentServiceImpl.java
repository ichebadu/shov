package com.iche.paymentservice.service.serviceImpl;

import com.iche.paymentservice.dto.response.WebhookResponse;
import com.iche.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final HttpHeaders httpHeaders;
    private final RestTemplate restTemplate;
    public WebhookResponse webhookResponse(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setCacheControl("no-cache");

      //  ResponseEntity<?> responseEntity =
        return webhookResponse();
    }

}

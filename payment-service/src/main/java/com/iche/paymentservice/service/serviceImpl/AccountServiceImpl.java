package com.iche.paymentservice.service.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iche.paymentservice.dto.request.AccountRequest;
import com.iche.paymentservice.dto.request.BankData;
import com.iche.paymentservice.dto.request.VerifyAccount;
import com.iche.paymentservice.dto.response.AccountData;
import com.iche.paymentservice.dto.response.PaystackResponse;
import com.iche.paymentservice.dto.response.VerifyAccountResponse;
import com.iche.paymentservice.model.Banks;
import com.iche.paymentservice.model.User;
import com.iche.paymentservice.repository.BanksRepository;
import com.iche.paymentservice.repository.UserRepository;
import com.iche.paymentservice.service.AccountService;
import com.iche.paymentservice.utils.PaystackUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URL;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements ApplicationRunner, AccountService {
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final ObjectMapper objectMapper;
    private final BanksRepository banksRepository;
    private final UserRepository userRepository;
    private final PaystackUtil paystackUtil;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setCacheControl("no-cache");
        RequestEntity<?> requestEntity = new RequestEntity<>(httpHeaders, HttpMethod.GET, URI.create(PaystackUtil.getPaystackurl()));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        try {
            PaystackResponse paystackResponse = objectMapper.readValue(responseEntity.getBody(), PaystackResponse.class);
            System.out.println(paystackResponse);
            List<BankData> bankDataList = paystackResponse.getData();
            banksRepository.deleteAll();

            bankDataList.forEach((data) -> {
               banksRepository.save(Banks.builder()
                        .bankCode(data.getCode())
                        .bankName(data.getName())
                        .build());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AccountRequest saveAccount(){
        return null;
    }
    public AccountData AccountNumberVerification(VerifyAccount verifyAccount) throws JsonProcessingException {
        Banks banks = banksRepository.findByBankName(verifyAccount.getBankName())
                .orElseThrow(()-> new RuntimeException("Bank not found"));

        System.out.println(banks.getBankCode());
        // this is the pay-stack secret key
        httpHeaders.setBearerAuth(paystackUtil.payStackSecretKey());

        String url = "https://api.paystack.co/bank/resolve?account_number="+verifyAccount.getAccountNumber()+"&bank_code="+banks.getBankCode();
        RequestEntity<?> requestEntity = new RequestEntity<>(httpHeaders,HttpMethod.GET,URI.create(url));
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        VerifyAccountResponse verifyAccountResponse= objectMapper.readValue(responseEntity.getBody(),VerifyAccountResponse.class);
        return verifyAccountResponse.getData();
    }
}

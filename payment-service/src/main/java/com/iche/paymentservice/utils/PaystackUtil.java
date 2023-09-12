package com.iche.paymentservice.utils;

import org.springframework.beans.factory.annotation.Value;

public class PaystackUtil {
    @Value("${paystack-secret-key}")
    private String payStackSecretKey;

    private static final  String PAYSTACKURL = "https://api.paystack.co/bank";
    public static String getPaystackurl (){
        return PAYSTACKURL;
    }
    public String payStackSecretKey(){
        return payStackSecretKey;
    }

}

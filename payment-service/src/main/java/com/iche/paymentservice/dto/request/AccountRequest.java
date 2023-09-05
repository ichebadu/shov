package com.iche.paymentservice.dto.request;

import lombok.Data;

@Data
public class AccountRequest {
    private String AccountName;
    private String BankName;
    private String AccountNumber;
}

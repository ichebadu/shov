package com.iche.paymentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iche.paymentservice.dto.request.VerifyAccount;
import com.iche.paymentservice.dto.response.AccountData;
import com.iche.paymentservice.dto.response.VerifyAccountResponse;

public interface AccountService  {
    AccountData AccountNumberVerification(VerifyAccount verifyAccount) throws JsonProcessingException;
}

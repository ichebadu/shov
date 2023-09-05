package com.iche.paymentservice.dto.response;

import com.iche.paymentservice.dto.request.BankData;
import lombok.Data;


import java.util.List;

@Data
public class PaystackResponse  {

    private String message;
    private String status;
    private List<BankData> data;

}

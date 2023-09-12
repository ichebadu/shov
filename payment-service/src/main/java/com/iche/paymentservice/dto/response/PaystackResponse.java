package com.iche.paymentservice.dto.response;

import com.iche.paymentservice.dto.request.BankData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaystackResponse  {

    private String message;
    private String status;
    private List<BankData> data;

}

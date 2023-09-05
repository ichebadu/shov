package com.iche.paymentservice.dto.response;

import com.iche.paymentservice.utils.DateUtils;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse <T>{
    private String message;
    private String time;
    private T data;

    public ApiResponse(T data){
        this.message= "processed successfully";
        this.time = DateUtils.saveDate(LocalDateTime.now());
        this.data = data;
    }
}

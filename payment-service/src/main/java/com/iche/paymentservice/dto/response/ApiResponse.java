package com.iche.paymentservice.dto.response;

import com.iche.paymentservice.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

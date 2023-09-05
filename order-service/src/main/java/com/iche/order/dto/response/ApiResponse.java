package com.iche.order.dto.response;

import com.iche.order.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse <T>{
    private String message;
    private String time;
    private T data;
    public ApiResponse(T data){
        this.message = "apiResponse received";
        this.time = DateUtils.saveDate(LocalDateTime.now());
        this.data= data;
    }
}

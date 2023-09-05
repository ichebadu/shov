package com.iche.product.dto.response;

import com.iche.product.utils.DateUtils;
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
    private T payload;

    public ApiResponse(T payload){
        this.message = "api received";
        this.time= DateUtils.saveDate(LocalDateTime.now());
        this.payload = payload;
    }

}

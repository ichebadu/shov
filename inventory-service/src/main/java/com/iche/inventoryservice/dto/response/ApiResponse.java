package com.iche.inventoryservice.dto.response;

import com.iche.inventoryservice.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse <T>{
    private String message;
    private String time;
    private T data;

    public ApiResponse(T data){
        this.message="api received";
        this.time = DateUtils.saveDate(LocalDateTime.now());
        this.data = data;
    }
}

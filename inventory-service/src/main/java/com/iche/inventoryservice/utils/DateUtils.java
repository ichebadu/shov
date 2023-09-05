package com.iche.inventoryservice.utils;


import lombok.Data;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DateUtils {
    public static String saveDate(LocalDateTime localDateTime){
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("YYYY-MM-DD hh:mm:ss a");
        return localDateTime.format(dateTimeFormat);
    }
}

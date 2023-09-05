package com.iche.paymentservice.utils;

import lombok.Data;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class DateUtils {

    public static String saveDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-DD hh:mm:ss a");
        return date.format(formatter);
    }
}

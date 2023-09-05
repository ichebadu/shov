package com.iche.paymentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Payments {

    @Id
    private String Id;
    @Column(nullable = false)
    private BigDecimal amount;
    private String purchaseDate;
    private String buyerName;
    private Integer qty;
    @OneToOne
    private EventTicket eventTicket;
    @OneToOne
    private User buyer;

}

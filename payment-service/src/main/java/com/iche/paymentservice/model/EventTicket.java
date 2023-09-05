package com.iche.paymentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class EventTicket {
    @Id
    private String Id;
    private String name;
    private String price;
    private Integer quantity;
    @ManyToOne
    private Event event;
}

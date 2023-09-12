package com.iche.paymentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Event {
    @Id
    private String id;
    private String ticketClass;
    private String description;
    private double ticketPrice;
    private Integer quantity;
    @ManyToOne
    private User creator;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Changed fetch type to LAZY
    private List<Product> productList;

}

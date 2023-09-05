package com.iche.paymentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="users")
public class User {
    @Id
    private String Id;
    private String name;
    private String email;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Account accounts;
    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Changed fetch type to LAZY
    private List<Event> events;
}

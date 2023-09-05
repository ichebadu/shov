package com.iche.paymentservice.repository;

import com.iche.paymentservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

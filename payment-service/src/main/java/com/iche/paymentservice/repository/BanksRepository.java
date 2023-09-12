package com.iche.paymentservice.repository;

import com.iche.paymentservice.model.Banks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BanksRepository extends JpaRepository<Banks,Long> {
    Optional<Banks> findByBankName(String bankName);
}

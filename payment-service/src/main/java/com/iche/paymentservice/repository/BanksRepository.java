package com.iche.paymentservice.repository;

import com.iche.paymentservice.model.Banks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanksRepository extends JpaRepository<Banks,Long> {
}

package com.forexapp.forexapplication.repository;

import com.forexapp.forexapplication.entity.ExchangeRate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;


@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Page<ExchangeRate> findByTransactionId(String transactionId, Pageable pageable);
    Page<ExchangeRate> findByTransactionDate(LocalDate transactionDate, Pageable pageable);
}

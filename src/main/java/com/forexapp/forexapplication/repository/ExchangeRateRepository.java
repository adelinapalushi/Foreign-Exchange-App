package com.forexapp.forexapplication.repository;

import com.forexapp.forexapplication.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    Optional<ExchangeRate> findBySourceCurrencyAndTargetCurrency(String source, String target);
}

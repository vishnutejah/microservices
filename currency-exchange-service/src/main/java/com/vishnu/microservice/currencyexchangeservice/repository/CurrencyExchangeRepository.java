package com.vishnu.microservice.currencyexchangeservice.repository;

import com.vishnu.microservice.currencyexchangeservice.pojo.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange,Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}

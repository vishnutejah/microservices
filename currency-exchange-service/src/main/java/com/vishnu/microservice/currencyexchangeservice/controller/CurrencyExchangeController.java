package com.vishnu.microservice.currencyexchangeservice.controller;

import com.vishnu.microservice.currencyexchangeservice.pojo.CurrencyExchange;
import com.vishnu.microservice.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("currency-exchange")
public class CurrencyExchangeController {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private Environment environment;

    @GetMapping("from/{from}/to/{to}")
    public CurrencyExchange retrieveExchange(@PathVariable("from") String from, @PathVariable("to") String to){
        String port = environment.getProperty("local.server.port");
        CurrencyExchange currencyExchange=currencyExchangeRepository.findByFromAndTo(from,to);
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}

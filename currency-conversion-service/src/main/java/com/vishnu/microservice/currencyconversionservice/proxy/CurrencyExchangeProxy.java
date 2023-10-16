package com.vishnu.microservice.currencyconversionservice.proxy;

import com.vishnu.microservice.currencyconversionservice.pojo.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Without Load balancing
//@FeignClient(name = "currency-exchange",url = "localhost:8000")
//With Load balancing
@FeignClient(name = "currency-exchange")
public interface CurrencyExchangeProxy {

    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchange(@PathVariable("from") String from, @PathVariable("to") String to);
}

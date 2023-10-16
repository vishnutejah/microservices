package com.vishnu.microservice.currencyconversionservice.controller;

import com.vishnu.microservice.currencyconversionservice.pojo.CurrencyConversion;
import com.vishnu.microservice.currencyconversionservice.proxy.CurrencyExchangeProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

/*Currency Conversion Service
        http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
        http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10*/
@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy currencyExchangeProxy;

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion retrieveConversion(@PathVariable("from") String from,
                                                 @PathVariable("to") String to,
                                                 @PathVariable("quantity") BigDecimal quantity){
        HashMap<String,String> uriVariables=new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        return new RestTemplate().
                getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",CurrencyConversion.class,uriVariables).
                getBody();

    }

    @GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion retrieveConversionFeign(@PathVariable("from") String from,
                                                 @PathVariable("to") String to,
                                                 @PathVariable("quantity") BigDecimal quantity){

        return currencyExchangeProxy.retrieveExchange(from,to);

    }

}

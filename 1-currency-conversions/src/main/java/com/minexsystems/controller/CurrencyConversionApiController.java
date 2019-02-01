package com.minexsystems.controller;

import com.minexsystems.entity.CurrencyName;
import com.minexsystems.entity.Rate;
import com.minexsystems.service.CurrencyConversionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/rates")
public class CurrencyConversionApiController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public CurrencyConversionApiController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @GetMapping
    public List<Rate> getAllRates() {
        logger.info("Retrieving all rates");
        long start = System.currentTimeMillis();
        List<Rate> rates = currencyConversionService.getAll();
        logger.info("Rates  are {}. It took {} ms", rates, System.currentTimeMillis() - start);
        return rates;
    }

    @GetMapping(value = "/rate")
    public Rate getRate(@RequestParam(value = "source", defaultValue = "EUR") CurrencyName sourceCurrency,
                        @RequestParam(value = "target", defaultValue = "EUR") CurrencyName targetCurrency) {
        logger.info("Retrieving rate for  source {} and target {}");
        long start = System.currentTimeMillis();
        Rate rate = currencyConversionService.getRate(sourceCurrency, targetCurrency);
        logger.info("Rate  is {}. It took {} ms", rate, System.currentTimeMillis() - start);
        return rate;
    }
}

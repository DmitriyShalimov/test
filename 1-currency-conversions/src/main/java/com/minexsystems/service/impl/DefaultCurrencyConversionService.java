package com.minexsystems.service.impl;

import com.minexsystems.entity.*;
import com.minexsystems.service.CurrencyConversionService;
import com.minexsystems.util.CurrencyLoader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;


@Service
public class DefaultCurrencyConversionService implements CurrencyConversionService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final CurrencyLoader currencyLoader;
    private List<Rate> rates = new ArrayList<>();

    @Autowired
    public DefaultCurrencyConversionService(CurrencyLoader currencyLoader) {
        this.currencyLoader = currencyLoader;
    }

    @Override
    public List<Rate> getAll() {
        return rates;
    }

    @Override
    public Rate getRate(CurrencyName sourceCurrency, CurrencyName targetCurrency) {
        if (sourceCurrency.equals(targetCurrency)) {
            Rate rate = new Rate();
            rate.setMidpoint(1);
            rate.setSourceCurrency(sourceCurrency);
            rate.setTargetCurrency(targetCurrency);
            return rate;
        }
        for (Rate rate : rates) {
            if (rate.getSourceCurrency().equals(sourceCurrency) && rate.getTargetCurrency().equals(targetCurrency)) {
                return rate;
            }
        }
        return null;
    }

    @PostConstruct
    @Scheduled(fixedDelayString = "${currency.update.time}", initialDelayString = "${currency.update.time}")
    private void getRatesFromAPI() {
        RatesResponseDto ratesResponseDto = currencyLoader.getRatesDto();
        calculateRates(ratesResponseDto);
    }

    private void calculateRates(RatesResponseDto ratesResponseDto) {
        Map<CurrencyName, RateToBaseCurrency> ratesToBaseCurrency = getRatesToBaseCurrencyMap(ratesResponseDto);
        calculateBaseRates(ratesResponseDto, ratesToBaseCurrency);
        calculateMissingRates(ratesToBaseCurrency);
        calculateReverseRates();
    }

    private void calculateBaseRates(RatesResponseDto ratesResponseDto, Map<CurrencyName, RateToBaseCurrency> ratesToBaseCurrency) {
        ratesToBaseCurrency.forEach((key, value) -> {
            Rate rate = new Rate();
            rate.setSourceCurrency(ratesResponseDto.getBaseCurrency());
            rate.setTargetCurrency(key);
            rate.setMidpoint(value.getMidpoint());
            rates.add(rate);
        });
    }

    private void calculateReverseRates() {
        List<Rate> reverseRates = new ArrayList<>();
        rates.forEach(rate -> {
            Rate reverseRate = new Rate();
            reverseRate.setSourceCurrency(rate.getTargetCurrency());
            reverseRate.setTargetCurrency(rate.getSourceCurrency());
            reverseRate.setMidpoint(1 / rate.getMidpoint());
            reverseRates.add(reverseRate);
        });
        rates.addAll(reverseRates);
    }

    private void calculateMissingRates(Map<CurrencyName, RateToBaseCurrency> ratesToBaseCurrency) {
        List<Map.Entry<CurrencyName, RateToBaseCurrency>> entries = new ArrayList<>(ratesToBaseCurrency.entrySet());
        for (int i = 0; i < entries.size() - 1; i++) {
            for (int j = i + 1; j < entries.size(); j++) {
                CurrencyName sourceCurrencyName = entries.get(i).getKey();
                CurrencyName targetCurrencyName = entries.get(j).getKey();
                double midpoint = entries.get(j).getValue().getMidpoint() / entries.get(i).getValue().getMidpoint();
                Rate rate = new Rate();
                rate.setSourceCurrency(sourceCurrencyName);
                rate.setTargetCurrency(targetCurrencyName);
                rate.setMidpoint(midpoint);
                rates.add(rate);
            }
        }
    }

    private Map<CurrencyName, RateToBaseCurrency> getRatesToBaseCurrencyMap(RatesResponseDto ratesResponseDto) {
        Quotes quotes = ratesResponseDto.getQuotes();
        Class clazz = quotes.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        Map<CurrencyName, RateToBaseCurrency> ratesToBaseCurrency = new HashMap<>();
        ratesToBaseCurrency.put(null,null);
        for (Method method : methods) {
            if (method.getName().contains("get")) {
                try {
                    RateToBaseCurrency rate = (RateToBaseCurrency) method.invoke(quotes);
                    ratesToBaseCurrency.put(CurrencyName.getCurrencyNameById(method.getName().substring(3).toLowerCase()), rate);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    logger.error("Error occurred while getting rates to base currency", e);
                }
            }
        }
        return ratesToBaseCurrency;
    }
}

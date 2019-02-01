package com.minexsystems.service;

import com.minexsystems.entity.CurrencyName;
import com.minexsystems.entity.Rate;

import java.util.List;

public interface CurrencyConversionService {
    List<Rate> getAll();

    Rate getRate(CurrencyName sourceCurrency, CurrencyName targetCurrency);
}

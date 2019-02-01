package com.minexsystems.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class EffectiveParams {
    private Date date;
    @JsonProperty("quote_currencies")
    private List<CurrencyName> quoteCurrencies;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<CurrencyName> getQuoteCurrencies() {
        return quoteCurrencies;
    }

    public void setQuoteCurrencies(List<CurrencyName> quoteCurrencies) {
        this.quoteCurrencies = quoteCurrencies;
    }
}

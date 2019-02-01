package com.minexsystems.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RatesResponseDto {
    @JsonProperty("base_currency")
    private CurrencyName baseCurrency;
    private Meta meta;
    private Quotes quotes;

    public Quotes getQuotes() {
        return quotes;
    }

    public void setQuotes(Quotes quotes) {
        this.quotes = quotes;
    }

    public CurrencyName getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(CurrencyName baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }


}

package com.minexsystems.entity;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Quotes {
    @JsonProperty("GBP")
    private RateToBaseCurrency gbp;
    @JsonProperty("UAH")
    private RateToBaseCurrency uah;
    @JsonProperty("USD")
    private RateToBaseCurrency usd;

    public RateToBaseCurrency getGbp() {
        return gbp;
    }

    public void setGbp(RateToBaseCurrency gbp) {
        this.gbp = gbp;
    }

    public RateToBaseCurrency getUah() {
        return uah;
    }

    public void setUah(RateToBaseCurrency uah) {
        this.uah = uah;
    }

    public RateToBaseCurrency getUsd() {
        return usd;
    }

    public void setUsd(RateToBaseCurrency usd) {
        this.usd = usd;
    }
}

package com.minexsystems.entity;

public class Rate {
    private CurrencyName sourceCurrency;
    private CurrencyName targetCurrency;
    private double midpoint;

    public double getMidpoint() {
        return midpoint;
    }

    public void setMidpoint(double midpoint) {
        this.midpoint = midpoint;
    }

    public CurrencyName getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(CurrencyName sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public CurrencyName getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(CurrencyName targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    @Override
    public String toString() {
        return "Rate{" +
                ", sourceCurrency=" + sourceCurrency +
                ", targetCurrency=" + targetCurrency +
                ", midpoint=" + midpoint +
                '}';
    }
}

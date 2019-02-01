package com.minexsystems.entity;

public enum CurrencyName {
    USD("usd"), EUR("eur"), UAH("uah"), GBP("gbp");
    private String id;

    CurrencyName(String id) {
        this.id = id;
    }

    public static CurrencyName getCurrencyNameById(String id) {
        for (CurrencyName currencyName : CurrencyName.values()) {
            if (currencyName.getId().equals(id)) {
                return currencyName;
            }
        }
        throw new IllegalArgumentException("No currencyName found for id = " + id);
    }

    public String getId() {
        return id;
    }
}
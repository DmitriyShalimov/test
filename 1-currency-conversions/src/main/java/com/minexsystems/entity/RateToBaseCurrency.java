package com.minexsystems.entity;

import java.util.Date;

public class RateToBaseCurrency {
    private Date date;
    private double midpoint;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getMidpoint() {
        return midpoint;
    }

    public void setMidpoint(double midpoint) {
        this.midpoint = midpoint;
    }
}

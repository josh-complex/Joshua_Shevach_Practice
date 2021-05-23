package com.company.gamestore.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Tax {

    private String state;
    private BigDecimal rate;

    public Tax() {}
    public Tax(String stateCode, BigDecimal taxRate){
        this.setState(stateCode);
        this.setRate(taxRate);
    }
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public BigDecimal getRate() {
        return rate.setScale(2, RoundingMode.HALF_UP);
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return Objects.equals(getState(), tax.getState()) &&
                Objects.equals(getRate(), tax.getRate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getRate());
    }
}

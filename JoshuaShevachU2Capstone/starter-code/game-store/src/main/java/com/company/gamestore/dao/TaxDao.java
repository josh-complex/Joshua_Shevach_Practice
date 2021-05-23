package com.company.gamestore.dao;

import com.company.gamestore.model.Tax;

import java.math.BigDecimal;

public interface TaxDao {

    Tax getTaxRateObject(String state);
    BigDecimal getTaxRate(String state);

}

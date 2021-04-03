package com.company.JoshuaShevachU1Capstone.dao;

import com.company.JoshuaShevachU1Capstone.model.SalesTaxRate;

public interface SalesTaxRateDao {

    SalesTaxRate getSalesTaxRateByState(String stateCode);

}

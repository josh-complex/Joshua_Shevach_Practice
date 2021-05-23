package com.company.gamestore.dao;

import com.company.gamestore.model.ProcessingFee;

import java.math.BigDecimal;

public interface ProcessingFeeDao {
    public ProcessingFee getProcessingFeeObject(String productType);

    public BigDecimal getProcessingFee(String productType);

}

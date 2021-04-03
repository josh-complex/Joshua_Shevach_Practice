package com.company.JoshuaShevachU1Capstone.dao;

import com.company.JoshuaShevachU1Capstone.model.ProcessingFee;

public interface ProcessingFeeDao {

    ProcessingFee getProcessingFeeByProductType(String productType);

}

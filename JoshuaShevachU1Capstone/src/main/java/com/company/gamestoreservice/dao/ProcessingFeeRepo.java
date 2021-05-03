package com.company.gamestoreservice.dao;

import com.company.gamestoreservice.model.ProcessingFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessingFeeRepo extends JpaRepository<ProcessingFee, String> {

    ProcessingFee findByProductType(String productType);

}

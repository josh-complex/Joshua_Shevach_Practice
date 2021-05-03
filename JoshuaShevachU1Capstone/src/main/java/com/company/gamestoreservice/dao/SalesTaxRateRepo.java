package com.company.gamestoreservice.dao;

import com.company.gamestoreservice.model.SalesTaxRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesTaxRateRepo extends JpaRepository<SalesTaxRate, String> {

    SalesTaxRate findByState(String stateCode);

}

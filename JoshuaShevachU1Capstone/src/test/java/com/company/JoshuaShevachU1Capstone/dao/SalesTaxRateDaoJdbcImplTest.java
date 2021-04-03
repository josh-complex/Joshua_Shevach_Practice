package com.company.JoshuaShevachU1Capstone.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalesTaxRateDaoJdbcImplTest {

    @Autowired
    SalesTaxRateDao salesTaxRateDao;

    @Test
    public void shouldGetTaxRateFromStateCode() {
        assertNotNull(salesTaxRateDao.getSalesTaxRateByState("FL"));
        assertNull(salesTaxRateDao.getSalesTaxRateByState("FG"));
        assertEquals(new BigDecimal("0.03"), salesTaxRateDao.getSalesTaxRateByState("ME").getRate());
    }

}

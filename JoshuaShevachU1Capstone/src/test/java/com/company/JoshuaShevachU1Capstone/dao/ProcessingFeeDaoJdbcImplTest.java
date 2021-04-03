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
public class ProcessingFeeDaoJdbcImplTest {

    @Autowired
    ProcessingFeeDao processingFeeDao;

    @Test
    public void shouldGetProcessingFeeByItemType() {
        assertNotNull(processingFeeDao.getProcessingFeeByProductType("Consoles"));
        assertNull(processingFeeDao.getProcessingFeeByProductType("Jellybeans"));
        assertEquals(new BigDecimal("1.98"), processingFeeDao.getProcessingFeeByProductType("T-Shirts").getFee());
    }

}

package com.company.gamestore.dao;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.ProcessingFee;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ProcessingFeeDaoJdbcTemplateImplTest {

    @Autowired
    ProcessingFeeDao processingFeeDao;

    @org.junit.Before
    public void setUp() throws Exception {
        //processingFeeDao.getAllConsoles().forEach(console -> conDao.deleteConsole(console.getId()));
    }

    @Test
    public void getProcessingFee() {
        //Arrange

        BigDecimal foundFee = processingFeeDao.getProcessingFee("T-Shirt");
        assertEquals(foundFee ,new BigDecimal("1.98"));

        foundFee = processingFeeDao.getProcessingFee("Console");
        assertEquals(foundFee ,new BigDecimal("14.99"));

        foundFee = processingFeeDao.getProcessingFee("Game");
        assertEquals(foundFee ,new BigDecimal("1.49"));
    }

    @Test
    public void getProcessingFeeObject() {
        ProcessingFee tShirtProcessingFee = new ProcessingFee();
        tShirtProcessingFee.setFee(new BigDecimal("1.98"));
        tShirtProcessingFee.setProductType("T-Shirt");

        ProcessingFee consoleProcessingFee = new ProcessingFee();
        consoleProcessingFee.setFee(new BigDecimal("14.99"));
        consoleProcessingFee.setProductType("Console");

        ProcessingFee gameProcessingFee = new ProcessingFee();
        gameProcessingFee.setFee(new BigDecimal("1.49"));
        gameProcessingFee.setProductType("Game");

        ProcessingFee foundProcessingFee = processingFeeDao.getProcessingFeeObject(tShirtProcessingFee.getProductType());
        assertEquals(tShirtProcessingFee,foundProcessingFee);

        foundProcessingFee = processingFeeDao.getProcessingFeeObject(consoleProcessingFee.getProductType());
        assertEquals(consoleProcessingFee,foundProcessingFee);

        foundProcessingFee = processingFeeDao.getProcessingFeeObject(gameProcessingFee.getProductType());
        assertEquals(gameProcessingFee,foundProcessingFee);
    }
}
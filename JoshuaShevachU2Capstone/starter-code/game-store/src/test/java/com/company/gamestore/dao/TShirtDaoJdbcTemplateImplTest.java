package com.company.gamestore.dao;

import com.company.gamestore.model.TShirt;
import com.company.gamestore.model.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.gamestore.model.TShirt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtDaoJdbcTemplateImplTest {

    @Autowired
    TShirtDao tShirtDao;

    @Before
    public void setUp() throws Exception {
        tShirtDao.getAllTShirts().forEach(tShirt -> tShirtDao.deleteTShirt(tShirt.getId()));
    }

    @Test
    public void shouldAddFindDeleteTShirt() {

        //Arrange
        TShirt newTShirt = new TShirt();
        newTShirt.setQuantity(1);
        newTShirt.setPrice( new BigDecimal("10.05"));
        newTShirt.setDescription("Everybody Knows Your Name");
        newTShirt.setColor("SkyBlue");
        newTShirt.setSize("M");

        //Act
        newTShirt = tShirtDao.addTShirt(newTShirt);
        TShirt foundCon = tShirtDao.getTShirt(newTShirt.getId());

        //Assert
        assertEquals(newTShirt,foundCon);

        //Arrange
        newTShirt.setQuantity(5);
        newTShirt.setDescription("Aint nobody got time for that!");

        //Act
        tShirtDao.updateTShirt(newTShirt);
        foundCon = tShirtDao.getTShirt(newTShirt.getId());

        //Assert
        assertEquals(newTShirt,foundCon);

        //Act
        tShirtDao.deleteTShirt(newTShirt.getId());
        foundCon = tShirtDao.getTShirt(newTShirt.getId());

        //Assert
        assertNull(foundCon);
    }

    @Test
    public void shouldFindAllTShirt(){
        //Arrange
        TShirt newTShirt1 = new TShirt();
        newTShirt1.setQuantity(1);
        newTShirt1.setPrice( new BigDecimal("10.05"));
        newTShirt1.setDescription("Everybody Knows Your Name");
        newTShirt1.setColor("SkyBlue");
        newTShirt1.setSize("M");

        TShirt newTShirt2 = new TShirt();
        newTShirt2.setQuantity(11);
        newTShirt2.setPrice( new BigDecimal("15.00"));
        newTShirt2.setDescription("I am not always right...");
        newTShirt2.setColor("Pink");
        newTShirt2.setSize("S");

        //Act
        newTShirt1 = tShirtDao.addTShirt(newTShirt1);
        newTShirt2 = tShirtDao.addTShirt(newTShirt2);
        List<TShirt> allTShirt = new ArrayList();
        allTShirt.add(newTShirt1);
        allTShirt.add(newTShirt2);

        //Act
        List<TShirt> foundAllTShirt = tShirtDao.getAllTShirts();

        //Assert
        assertEquals(allTShirt.size(),foundAllTShirt.size());
    }

    /*
    List<TShirt> getAllTShirtsByColor(String color);

    List<TShirt> getAllTShirtsBySize(String size);
     */
    @Test
    public void shouldFindTShirtByColor(){
        //Arrange
        TShirt newTShirt1 = new TShirt();
        newTShirt1.setQuantity(1);
        newTShirt1.setPrice( new BigDecimal("10.05"));
        newTShirt1.setDescription("Everybody Knows Your Name");
        newTShirt1.setColor("SkyBlue");
        newTShirt1.setSize("M");

        TShirt newTShirt2 = new TShirt();
        newTShirt2.setQuantity(11);
        newTShirt2.setPrice( new BigDecimal("15.00"));
        newTShirt2.setDescription("I am not always right...");
        newTShirt2.setColor("Pink");
        newTShirt2.setSize("S");

        TShirt newTShirt3 = new TShirt();
        newTShirt3.setQuantity(9);
        newTShirt3.setPrice( new BigDecimal("19.00"));
        newTShirt3.setDescription("Trust me I am a Pro...crastinator");
        newTShirt3.setColor("Pink");
        newTShirt3.setSize("L");

        //Act
        newTShirt1 = tShirtDao.addTShirt(newTShirt1);
        newTShirt2 = tShirtDao.addTShirt(newTShirt2);
        newTShirt3 = tShirtDao.addTShirt(newTShirt3);

        //Act
        List<TShirt> foundNoTShirt = tShirtDao.getAllTShirtsByColor("InvalidValue");

        List<TShirt> foundOneTShirt = tShirtDao.getAllTShirtsByColor("SkyBlue");

        List<TShirt> foundTwoTShirt = tShirtDao.getAllTShirtsByColor("Pink");

        //Assert
        assertEquals(foundNoTShirt.size(),0);
        assertEquals(foundOneTShirt.size(),1);
        assertEquals(foundTwoTShirt.size(),2);

    }
    @Test
    public void shouldFindTShirtBySize(){
        //Arrange
        TShirt newTShirt1 = new TShirt();
        newTShirt1.setQuantity(1);
        newTShirt1.setPrice( new BigDecimal("10.05"));
        newTShirt1.setDescription("Everybody Knows Your Name");
        newTShirt1.setColor("SkyBlue");
        newTShirt1.setSize("L");

        TShirt newTShirt2 = new TShirt();
        newTShirt2.setQuantity(11);
        newTShirt2.setPrice( new BigDecimal("15.00"));
        newTShirt2.setDescription("I am not always right...");
        newTShirt2.setColor("Pink");
        newTShirt2.setSize("S");

        TShirt newTShirt3 = new TShirt();
        newTShirt3.setQuantity(9);
        newTShirt3.setPrice( new BigDecimal("19.00"));
        newTShirt3.setDescription("Trust me I am a Pro...crastinator");
        newTShirt3.setColor("Pink");
        newTShirt3.setSize("L");

        //Act
        newTShirt1 = tShirtDao.addTShirt(newTShirt1);
        newTShirt2 = tShirtDao.addTShirt(newTShirt2);
        newTShirt3 = tShirtDao.addTShirt(newTShirt3);

        //Act
        List<TShirt> foundNoTShirt = tShirtDao.getAllTShirtsBySize("InvalidValue");

        List<TShirt> foundOneTShirt = tShirtDao.getAllTShirtsBySize("S");

        List<TShirt> foundTwoTShirt = tShirtDao.getAllTShirtsBySize("L");

        //Assert
        assertEquals(foundNoTShirt.size(),0);
        assertEquals(foundOneTShirt.size(),1);
        assertEquals(foundTwoTShirt.size(),2);
    }
}
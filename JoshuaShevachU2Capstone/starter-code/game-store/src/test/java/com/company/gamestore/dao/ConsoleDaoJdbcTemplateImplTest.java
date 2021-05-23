package com.company.gamestore.dao;

import com.company.gamestore.model.Console;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleDaoJdbcTemplateImplTest {

    @Autowired
    ConsoleDao conDao;

    @org.junit.Before
    public void setUp() throws Exception {
        conDao.getAllConsoles().forEach(console -> conDao.deleteConsole(console.getId()));
    }

    @org.junit.Test
    public void shouldAddFindDeleteConsole() {

        //Arrange
        Console newCon = new Console();
        newCon.setQuantity(1);
        newCon.setPrice( new BigDecimal("10.05"));
        newCon.setProcessor("AMD");
        newCon.setMemoryAmount("2GB");
        newCon.setManufacturer("Sega");
        newCon.setModel("P3");

        //Act
        newCon = conDao.addConsole(newCon);
        Console foundCon = conDao.getConsole(newCon.getId());

        //Assert
        assertEquals(newCon,foundCon);

        //Arrange
        newCon.setQuantity(5);
        newCon.setProcessor("Intl");

        //Act
        conDao.updateConsole(newCon);
        foundCon = conDao.getConsole(newCon.getId());

        //Assert
        assertEquals(newCon,foundCon);

        //Act
        conDao.deleteConsole(newCon.getId());
        foundCon = conDao.getConsole(newCon.getId());

        //Assert
        assertNull(foundCon);
    }

    @org.junit.Test
    public void shouldFindAllConsole(){
        //Arrange
        Console newCon1 = new Console();
        newCon1.setQuantity(1);
        newCon1.setPrice( new BigDecimal("10.05"));
        newCon1.setProcessor("AMD");
        newCon1.setMemoryAmount("2GB");
        newCon1.setManufacturer("Sega");
        newCon1.setModel("P3");

        Console newCon2 = new Console();
        newCon2.setQuantity(5);
        newCon2.setPrice( new BigDecimal("11.05"));
        newCon2.setProcessor("AMD");
        newCon2.setMemoryAmount("2GB");
        newCon2.setManufacturer("PS-IV");
        newCon2.setModel("P3");

        //Act
        newCon1 = conDao.addConsole(newCon1);
        newCon2 = conDao.addConsole(newCon2);
        List<Console> allConsole = new ArrayList();
        allConsole.add(newCon1);
        allConsole.add(newCon2);

        //Act
        List<Console> foundAllConsole = conDao.getAllConsoles();

        //Assert
        assertEquals(allConsole.size(),foundAllConsole.size());
    }

    @org.junit.Test
    public void shouldFindConsoleByManufacturer(){
        //Arrange
        Console newCon1 = new Console();
        newCon1.setQuantity(1);
        newCon1.setPrice( new BigDecimal("10.05"));
        newCon1.setProcessor("AMD");
        newCon1.setMemoryAmount("2GB");
        newCon1.setManufacturer("Sega");
        newCon1.setModel("P3");

        Console newCon2 = new Console();
        newCon2.setQuantity(5);
        newCon2.setPrice( new BigDecimal("11.05"));
        newCon2.setProcessor("AMD");
        newCon2.setMemoryAmount("2GB");
        newCon2.setManufacturer("PS-IV");
        newCon2.setModel("P3");

        Console newCon3 = new Console();
        newCon3.setQuantity(5);
        newCon3.setPrice( new BigDecimal("41.05"));
        newCon3.setProcessor("Intel");
        newCon3.setMemoryAmount("8GB");
        newCon3.setManufacturer("PS-IV");
        newCon3.setModel("P3");
        //Act
        newCon1 = conDao.addConsole(newCon1);
        newCon2 = conDao.addConsole(newCon2);
        newCon3 = conDao.addConsole(newCon3);

        //Act
        List<Console> foundNoConsole = conDao.getConsolesByManufacturer("InvalidManufacturer");

        List<Console> foundOneConsole = conDao.getConsolesByManufacturer("Sega");

        List<Console> foundTwoConsole = conDao.getConsolesByManufacturer("PS-IV");

        //Assert
        assertEquals(foundNoConsole.size(),0);
        assertEquals(foundOneConsole.size(),1);
        assertEquals(foundTwoConsole.size(),2);
    }
}
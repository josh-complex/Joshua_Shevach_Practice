package com.company.JoshuaShevachU1Capstone.dao;

import com.company.JoshuaShevachU1Capstone.model.Tshirt;
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
public class TshirtDaoJdbcImplTest {

    @Autowired
    TshirtDao tshirtDao;

    private Tshirt tshirt;

    @Before
    public void setUp() {
        tshirtDao.getAllTshirts()
                .forEach(x -> tshirtDao.deleteTshirt(x.getId()));

        tshirt = new Tshirt();
        tshirt.setSize("Medium");
        tshirt.setColor("White");
        tshirt.setDescription("Cool comfort fabrics");
        tshirt.setItemType("T-Shirts");
        tshirt.setPrice(new BigDecimal("52.95"));
        tshirt.setRemainingInventory(32);
    }

    @Test
    public void shouldAddGetDeleteTshirt() {
        tshirt = tshirtDao.addTshirt(tshirt);

        assertEquals(tshirt, tshirtDao.getTshirt(tshirt.getId()));

        tshirtDao.deleteTshirt(tshirt.getId());

        assertNull(tshirtDao.getTshirt(tshirt.getId()));
    }

    @Test
    public void shouldGetAllTshirts() {
        tshirtDao.addTshirt(tshirt);

        assertEquals(1, tshirtDao.getAllTshirts().size());
    }

    @Test
    public void shouldGetTshirtsByColor() {
        tshirtDao.addTshirt(tshirt);

        assertEquals(1, tshirtDao.getTshirtsByColor("White").size());
    }

    @Test
    public void shouldGetTshirtsBySize() {
        tshirtDao.addTshirt(tshirt);

        assertEquals(1, tshirtDao.getTshirtsBySize("Medium").size());
    }

    @Test
    public void shouldUpdateTshirt() {
        tshirt = tshirtDao.addTshirt(tshirt);

        tshirt.setRemainingInventory(12);
        tshirt.setSize("Large");

        tshirtDao.updateTshirt(tshirt);

        tshirt = tshirtDao.getTshirt(tshirt.getId());

        assertEquals("Large", tshirt.getSize());
        assertNotEquals(32, tshirt.getRemainingInventory());
    }

}

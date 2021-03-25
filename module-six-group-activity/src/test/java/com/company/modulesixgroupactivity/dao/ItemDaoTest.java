package com.company.modulesixgroupactivity.dao;

import com.company.modulesixgroupactivity.model.Item;
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
public class ItemDaoTest {

    @Autowired
    private ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        itemDao.getAllItems()
                .forEach(x -> itemDao.deleteItem(x.getItemId()));
    }

    @Test
    public void shouldAddGetDeleteItem() {
        Item item = new Item(
                0,
                "Deep Tissue Massager",
                "Massage all the knots out of everywhere",
                new BigDecimal("3.95")
        );

        item = itemDao.addItem(item);

        Item item2 = itemDao.getItem(item.getItemId());

        assertEquals(item, item2);

        itemDao.deleteItem(item.getItemId());

        item2 = itemDao.getItem(item.getItemId());

        assertNull(item2);
    }

    @Test
    public void shouldGetAllItems() {
        Item item = new Item(
                0,
                "Deep Tissue Massager",
                "Massage all the knots out of everywhere",
                new BigDecimal("3.95")
        );

        item = itemDao.addItem(item);

        assertEquals(1, itemDao.getAllItems().size());

        itemDao.deleteItem(item.getItemId());

        assertEquals(0, itemDao.getAllItems().size());
    }

    @Test
    public void shouldUpdateItem() {
        Item item = new Item(
                0,
                "Deep Tissue Massager",
                "Massage all the knots out of everywhere",
                new BigDecimal("3.95")
        );

        item = itemDao.addItem(item);

        item.setName("XBox Controller");
        item.setDescription("Control your XBox like an XBoss");
        item.setDailyRate(new BigDecimal("1.99"));

        itemDao.updateItem(item);

        Item item2 = itemDao.getItem(item.getItemId());

        assertEquals("Control your XBox like an XBoss", item2.getDescription());
    }
}
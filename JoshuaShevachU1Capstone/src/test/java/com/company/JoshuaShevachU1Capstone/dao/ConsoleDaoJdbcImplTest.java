package com.company.JoshuaShevachU1Capstone.dao;

import com.company.JoshuaShevachU1Capstone.model.Console;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleDaoJdbcImplTest {

    @Autowired
    ConsoleDao consoleDao;

    private Console console;

    @Before
    public void setUp() {
        consoleDao.getAllConsoles()
                .forEach(x -> consoleDao.deleteConsole(x.getId()));

        console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("4 GB");
        console.setProcessor("Nvidia Tegra X1");
        console.setItemType("Consoles");
        console.setPrice(new BigDecimal("299.00"));
        console.setRemainingInventory(1);
    }

    @Test
    public void shouldAddGetDeleteConsole() {
        console = consoleDao.addConsole(console);

        assertEquals(console, consoleDao.getConsole(console.getId()));

        consoleDao.deleteConsole(console.getId());

        assertNull(consoleDao.getConsole(console.getId()));
    }

    @Test
    public void shouldGetAllConsoles() {
        consoleDao.addConsole(console);

        assertEquals(1, consoleDao.getAllConsoles().size());
    }

    @Test
    public void shouldGetConsolesByManufacturer() {
        consoleDao.addConsole(console);

        List<Console> consoles = consoleDao.getAllConsoles();

        assertEquals(1, consoles.size());
    }

    @Test
    public void shouldUpdateConsole() {
        console = consoleDao.addConsole(console);

        console.setRemainingInventory(2);
        console.setPrice(new BigDecimal("500.00"));

        consoleDao.updateConsole(console);

        console = consoleDao.getConsole(console.getId());

        assertEquals(2, console.getRemainingInventory());
        assertEquals(new BigDecimal("500.00"), console.getPrice());
    }

}

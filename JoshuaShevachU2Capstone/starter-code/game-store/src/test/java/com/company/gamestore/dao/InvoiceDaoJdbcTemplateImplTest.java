package com.company.gamestore.dao;

import com.company.gamestore.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

public class InvoiceDaoJdbcTemplateImplTest {

    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    TShirtDao tShirtDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    TaxDao taxDao;
    @Autowired
    ProcessingFeeDao processingFeeDao;

    @Before
    public void setUp() throws Exception {
        List<Console> consoleList = consoleDao.getAllConsoles();
        consoleList.stream()
                .forEach(i -> consoleDao.deleteConsole(i.getId()));

        List<Game> gameList = gameDao.getAllGames();
        gameList.stream()
                .forEach(i -> gameDao.deleteGame(i.getId()));

        List<TShirt> tShirtList = tShirtDao.getAllTShirts();
        tShirtList.stream()
                .forEach(i -> tShirtDao.deleteTShirt(i.getId()));

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        invoiceList.stream()
                .forEach(i -> invoiceDao.deleteInvoice(i.getId()));
    }

    @Test
    public void shouldAddFindDeleteInvoice() {

        //Arrange
        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("M");
        tShirt1.setColor("Blue");
        tShirt1.setDescription("v-neck short sleeve");

        //The double quotes forces the decimal point.
        // an alternative to set BigDecimal is using:
        // tShirt1.setPrice(new BigDecimal("15.99").setScale(2, RoundingMode.HALF_UP));
        tShirt1.setPrice(new BigDecimal("15.99"));

        tShirt1.setQuantity(8);
        tShirt1 = tShirtDao.addTShirt(tShirt1);

        Invoice invoice1 = new Invoice();
        invoice1.setName("Joe Black");
        invoice1.setStreet("123 Main St");
        invoice1.setCity("any City");
        invoice1.setState("NY");
        invoice1.setZipcode("10016");
        invoice1.setItemType("T-Shirt");
        invoice1.setItemId(tShirt1.getId());
        invoice1.setUnitPrice(tShirt1.getPrice());
        invoice1.setQuantity(2);

        invoice1.setSubtotal(
                tShirt1.getPrice().multiply(
                        new BigDecimal(invoice1.getQuantity()))
        );
        invoice1.setTax(
                invoice1.getSubtotal().multiply(taxDao.getTaxRate(invoice1.getState())));

        invoice1.setProcessingFee(processingFeeDao.getProcessingFee(invoice1.getItemType()));

        invoice1.setTotal(invoice1.getSubtotal().add(invoice1.getTax()).add(invoice1.getProcessingFee()));

        //Act
        invoice1 = invoiceDao.addInvoice(invoice1);
        Invoice invoice2 = invoiceDao.getInvoice(invoice1.getId());

        //Assert
        assertEquals(invoice1, invoice2);

        //Act
        invoiceDao.deleteInvoice(invoice1.getId());
        invoice2 = invoiceDao.getInvoice(invoice1.getId());

        //Assert
        assertNull(invoice2);
    }

    @Test
    public void shouldFindByName() {

        //Arrange
        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("M");
        tShirt1.setColor("Blue");
        tShirt1.setDescription("v-neck short sleeve");

        //The double quotes forces the decimal point.
        //an alternative to set BigDecimal is using:
        //tShirt1.setPrice(new BigDecimal("15.99").setScale(2, RoundingMode.HALF_UP));
        tShirt1.setPrice(new BigDecimal("15.99"));

        tShirt1.setQuantity(8);
        tShirt1 = tShirtDao.addTShirt(tShirt1);

        Invoice invoice1 = new Invoice();
        invoice1.setName("Joe Black");
        invoice1.setStreet("123 Main St");
        invoice1.setCity("any City");
        invoice1.setState("NY");
        invoice1.setZipcode("10016");
        invoice1.setItemType("T-Shirt");
        invoice1.setItemId(tShirt1.getId());
        invoice1.setUnitPrice(tShirt1.getPrice());
        invoice1.setQuantity(2);

        invoice1.setSubtotal(tShirt1.getPrice().multiply(new BigDecimal(invoice1.getQuantity())));

        invoice1.setTax(invoice1.getSubtotal().multiply(taxDao.getTaxRate(invoice1.getState())));

        invoice1.setProcessingFee(processingFeeDao.getProcessingFee(invoice1.getItemType()));

        invoice1.setTotal(invoice1.getSubtotal().add(invoice1.getTax()).add(invoice1.getProcessingFee()));

        //Act
        invoice1 = invoiceDao.addInvoice(invoice1);

        List<Invoice> foundNoinvoice = invoiceDao.getAllInvoicesByCustomerName("invalidValue");

        List<Invoice> foundOneinvoice = invoiceDao.getAllInvoicesByCustomerName(invoice1.getName());

        //Assert
        assertEquals(foundOneinvoice.size(),1);

        //Assert
        assertEquals(foundNoinvoice.size(),0);
    }
}
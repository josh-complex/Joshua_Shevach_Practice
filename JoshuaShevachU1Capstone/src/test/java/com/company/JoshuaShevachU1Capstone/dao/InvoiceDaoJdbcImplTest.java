package com.company.JoshuaShevachU1Capstone.dao;

import com.company.JoshuaShevachU1Capstone.model.Invoice;
import com.company.JoshuaShevachU1Capstone.model.ProcessingFee;
import com.company.JoshuaShevachU1Capstone.model.SalesTaxRate;
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
public class InvoiceDaoJdbcImplTest {

    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    ProcessingFeeDao processingFeeDao;
    @Autowired
    SalesTaxRateDao salesTaxRateDao;


    private Invoice invoice;

    @Before
    public void setUp() {
        invoiceDao.getAllInvoices()
                .forEach(x -> invoiceDao.deleteInvoice(x.getId()));

        invoice = new Invoice();
        invoice.setName("Joshua Shevach");
        invoice.setCity("Orlando");
        invoice.setState("FL");
        invoice.setStreet("1110 Bassano Way");
        invoice.setZipcode("32828");
        invoice.setItemId(12);
        invoice.setItemType("Consoles");
        invoice.setUnitPrice(new BigDecimal("299.00"));
        invoice.setQuantity(2);
    }

    @Test
    public void shouldCreateInvoice() {
        SalesTaxRate taxRate = salesTaxRateDao.getSalesTaxRateByState(invoice.getState());
        invoice.setTax(taxRate.getRate());

        ProcessingFee fee = processingFeeDao.getProcessingFeeByProductType(invoice.getItemType());
        invoice.setProcessingFee(fee.getFee());

        invoice.setSubtotal(invoice.getUnitPrice().multiply(new BigDecimal(invoice.getQuantity())));

        invoice.setTotal(invoice.getSubtotal().add(invoice.getTax()).add(invoice.getProcessingFee()));

        invoice = invoiceDao.addInvoice(invoice);

        assertEquals(invoice, invoiceDao.getInvoice(invoice.getId()));

        invoiceDao.deleteInvoice(invoice.getId());

        assertNull(invoiceDao.getInvoice(invoice.getId()));
    }

}

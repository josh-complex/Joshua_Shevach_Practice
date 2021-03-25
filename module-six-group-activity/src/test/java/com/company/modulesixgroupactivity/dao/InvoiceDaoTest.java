package com.company.modulesixgroupactivity.dao;

import com.company.modulesixgroupactivity.model.Customer;
import com.company.modulesixgroupactivity.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        invoiceDao.getAllInvoices()
                .forEach(x -> invoiceDao.deleteInvoice(x.getInvoiceId()));

        customerDao.getAllCustomers()
                .forEach(x -> customerDao.deleteCustomer(x.getCustomerId()));
    }

    @Test
    public void shouldAddGetDeleteInvoice() {
        Customer customer = new Customer(
                0,
                "Tiani",
                "Edwards",
                "tiani@heckyeah.com",
                "Cognizant",
                "8675309"
        );

        customer = customerDao.addCustomer(customer);

        Invoice invoice = new Invoice(
                0,
                customer.getCustomerId(),
                LocalDate.of(2021, 02, 12),
                LocalDate.of(2021, 02, 14),
                LocalDate.of(2021, 02, 15),
                new BigDecimal("50.00")
        );

        invoice = invoiceDao.addInvoice(invoice);

        List<Invoice> invoices = invoiceDao.getInvoicesByCustomer(customer);

        assertTrue(invoices.contains(invoice));

        invoiceDao.getInvoicesByCustomer(customer)
                .forEach(x -> invoiceDao.deleteInvoice(x.getInvoiceId()));

        invoices = invoiceDao.getInvoicesByCustomer(customer);

        assertEquals(0, invoices.size());
    }
}
package com.company.modulesixgroupactivity.dao;

import com.company.modulesixgroupactivity.model.Customer;
import com.company.modulesixgroupactivity.model.Invoice;
import com.company.modulesixgroupactivity.model.InvoiceItem;
import com.company.modulesixgroupactivity.model.Item;
import lombok.var;
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
public class InvoiceItemDaoTest {

    @Autowired
    private InvoiceItemDao invoiceItemDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private InvoiceDao invoiceDao;

    @Before
    public void setUp() throws Exception {
        invoiceItemDao.getAllInvoiceItems()
                .forEach(x -> invoiceItemDao.deleteInvoiceItem(x.getInvoiceItemId()));
        invoiceDao.getAllInvoices()
                .forEach(x -> invoiceDao.deleteInvoice(x.getInvoiceId()));
        itemDao.getAllItems()
                .forEach(x -> itemDao.deleteItem(x.getItemId()));
        customerDao.getAllCustomers()
                .forEach(x -> customerDao.deleteCustomer(x.getCustomerId()));
    }

    @Test
    public void shouldAddGetDeleteInvoiceItem() {
        Customer customer = customerDao.addCustomer(new Customer(
                0,
                "Tiani",
                "Edwards",
                "tiani@heckyeah.com",
                "Cognizant",
                "8675309"
        ));

        Item item = itemDao.addItem(new Item(
                0,
                "Deep Tissue Massager",
                "Massage all the knots out of everywhere",
                new BigDecimal("3.95")
        ));

        Invoice invoice = invoiceDao.addInvoice(new Invoice(
                0,
                customer.getCustomerId(),
                LocalDate.of(2021, 02, 12),
                LocalDate.of(2021, 02, 14),
                LocalDate.of(2021, 02, 15),
                new BigDecimal("50.00")
        ));

        BigDecimal itemQuantity = new BigDecimal("2");
        int actualQuantity = Integer.parseInt(itemQuantity.toString());

        InvoiceItem invoiceItem = invoiceItemDao.addInvoiceItem(new InvoiceItem(
                0,
                invoice.getInvoiceId(),
                item.getItemId(),
                actualQuantity,
                item.getDailyRate().multiply(itemQuantity),
                new BigDecimal("0.00")
        ));

        InvoiceItem invoiceItem2 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceItemId());

        assertEquals(invoiceItem, invoiceItem2);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId());

        invoiceItem2 = invoiceItemDao.getInvoiceItem(invoiceItem.getInvoiceId());

        assertNull(invoiceItem2);
    }

    @Test
    public void shouldGetInvoiceItemsByInvoiceId() {
        Customer customer = customerDao.addCustomer(new Customer(
                0,
                "Tiani",
                "Edwards",
                "tiani@heckyeah.com",
                "Cognizant",
                "8675309"
        ));

        Item item = itemDao.addItem(new Item(
                0,
                "Deep Tissue Massager",
                "Massage all the knots out of everywhere",
                new BigDecimal("3.95")
        ));

        Invoice invoice = invoiceDao.addInvoice(new Invoice(
                0,
                customer.getCustomerId(),
                LocalDate.of(2021, 02, 12),
                LocalDate.of(2021, 02, 14),
                LocalDate.of(2021, 02, 15),
                new BigDecimal("50.00")
        ));

        BigDecimal itemQuantity = new BigDecimal("2");
        int actualQuantity = Integer.parseInt(itemQuantity.toString());

        InvoiceItem invoiceItem = invoiceItemDao.addInvoiceItem(new InvoiceItem(
                0,
                invoice.getInvoiceId(),
                item.getItemId(),
                actualQuantity,
                item.getDailyRate().multiply(itemQuantity),
                new BigDecimal("0.00")
        ));

        Customer customer2 = customerDao.addCustomer(new Customer(
                0,
                "Tiani",
                "Edwards",
                "tiani@heckyeah.com",
                "Cognizant",
                "8675309"
        ));

        Item item2 = itemDao.addItem(new Item(
                0,
                "Deep Tissue Massager",
                "Massage all the knots out of everywhere",
                new BigDecimal("3.95")
        ));

        Invoice invoice2 = invoiceDao.addInvoice(new Invoice(
                0,
                customer2.getCustomerId(),
                LocalDate.of(2021, 02, 12),
                LocalDate.of(2021, 02, 14),
                LocalDate.of(2021, 02, 15),
                new BigDecimal("50.00")
        ));

        itemQuantity = new BigDecimal("2");
        actualQuantity = Integer.parseInt(itemQuantity.toString());

        InvoiceItem invoiceItem2 = invoiceItemDao.addInvoiceItem(new InvoiceItem(
                0,
                invoice2.getInvoiceId(),
                item2.getItemId(),
                actualQuantity,
                item2.getDailyRate().multiply(itemQuantity),
                new BigDecimal("0.00")
        ));

        Customer customer3 = customerDao.addCustomer(new Customer(
                0,
                "Tiani",
                "Edwards",
                "tiani@heckyeah.com",
                "Cognizant",
                "8675309"
        ));

        Item item3 = itemDao.addItem(new Item(
                0,
                "Deep Tissue Massager",
                "Massage all the knots out of everywhere",
                new BigDecimal("3.95")
        ));

        itemQuantity = new BigDecimal("2");
        actualQuantity = Integer.parseInt(itemQuantity.toString());

        InvoiceItem invoiceItem3 = invoiceItemDao.addInvoiceItem(new InvoiceItem(
                0,
                invoice2.getInvoiceId(),
                item3.getItemId(),
                actualQuantity,
                item3.getDailyRate().multiply(itemQuantity),
                new BigDecimal("0.00")
        ));

        List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItemsByInvoiceId(invoice2.getInvoiceId());

        assertEquals(2, invoiceItems.size());

    }
}
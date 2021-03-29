package com.company.modulesixgroupactivity.service;

import com.company.modulesixgroupactivity.dao.*;
import com.company.modulesixgroupactivity.model.Customer;
import com.company.modulesixgroupactivity.model.Invoice;
import com.company.modulesixgroupactivity.model.InvoiceItem;
import com.company.modulesixgroupactivity.model.Item;
import com.company.modulesixgroupactivity.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceTest {

    private Service service;
    private CustomerDao customerDao;
    private ItemDao itemDao;
    private InvoiceDao invoiceDao;
    private InvoiceItemDao invoiceItemDao;

    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        setUpItemDaoMock();
        setUpInvoiceDaoMock();
        setUpInvoiceItemDaoMock();

        service = new Service(customerDao, invoiceDao, itemDao, invoiceItemDao);
    }

    @Test
    public void shouldSaveInvoice() {
        InvoiceViewModel expectedInvoiceViewModel = new InvoiceViewModel();
        expectedInvoiceViewModel.setId(1);
        expectedInvoiceViewModel.setLateFee(new BigDecimal("50.00"));
        expectedInvoiceViewModel.setOrderDate(LocalDate.of(2021,2,12));
        expectedInvoiceViewModel.setPickupDate(LocalDate.of(2021,2,14));
        expectedInvoiceViewModel.setReturnDate(LocalDate.of(2021,2,15));

        Customer expectedCustomer = new Customer();
        expectedCustomer.setCustomerId(1);
        expectedInvoiceViewModel.setCustomer(expectedCustomer);

        InvoiceItem invoiceItem = new InvoiceItem(
                1,
                1,
                1,
                1,
                new BigDecimal("1"),
                new BigDecimal("0.00")
        );

        List<InvoiceItem> expectedInvoiceItems = new ArrayList<>();
        expectedInvoiceItems.add(invoiceItem);
        expectedInvoiceViewModel.setItems(expectedInvoiceItems);

        InvoiceViewModel inputInvoiceViewModel = new InvoiceViewModel();
        inputInvoiceViewModel.setLateFee(new BigDecimal("50.00"));
        inputInvoiceViewModel.setOrderDate(LocalDate.of(2021,2,12));
        inputInvoiceViewModel.setPickupDate(LocalDate.of(2021,2,14));
        inputInvoiceViewModel.setReturnDate(LocalDate.of(2021,2,15));

        Customer inputCustomer = new Customer();
        inputCustomer.setCustomerId(1);
        inputInvoiceViewModel.setCustomer(expectedCustomer);

        Invoice inputInvoice = new Invoice();
        inputInvoice.setInvoiceId(1);

        List<InvoiceItem> inputInvoiceItems = new ArrayList<>();
        inputInvoiceItems.add(invoiceItem);
        inputInvoiceViewModel.setItems(inputInvoiceItems);

        InvoiceViewModel resultOfSavingInput = service.saveInvoice(inputInvoiceViewModel);

        assertEquals(expectedInvoiceViewModel, resultOfSavingInput);
    }

    @Test
    public void shouldFindInvoice(){
        InvoiceViewModel expectedInvoiceViewModel = new InvoiceViewModel();
        expectedInvoiceViewModel.setId(1);
        expectedInvoiceViewModel.setLateFee(new BigDecimal("50.00"));
        expectedInvoiceViewModel.setOrderDate(LocalDate.of(2021,2,12));
        expectedInvoiceViewModel.setPickupDate(LocalDate.of(2021,2,14));
        expectedInvoiceViewModel.setReturnDate(LocalDate.of(2021,2,15));

        Customer expectedCustomer = new Customer(
                1,
                "Tiani",
                "Edwards",
                "tiani@heckyeah.com",
                "Cognizant",
                "8675309"
        );
        expectedInvoiceViewModel.setCustomer(expectedCustomer);

        InvoiceItem invoiceItem = new InvoiceItem(
                1,
                1,
                1,
                1,
                new BigDecimal("1"),
                new BigDecimal("0.00")
        );

        List<InvoiceItem> expectedInvoiceItems = new ArrayList<>();
        expectedInvoiceItems.add(invoiceItem);
        expectedInvoiceViewModel.setItems(expectedInvoiceItems);

        InvoiceViewModel resultOfRetrievingInvoice = service.findInvoice(1);

        assertEquals(expectedInvoiceViewModel,resultOfRetrievingInvoice);
    }

    @Test
    public void shouldFindAllInvoices() {
        InvoiceViewModel expectedInvoiceViewModel = new InvoiceViewModel();
        expectedInvoiceViewModel.setId(1);
        expectedInvoiceViewModel.setOrderDate(LocalDate.of(2021, 02, 12));
        expectedInvoiceViewModel.setPickupDate(LocalDate.of(2021, 02, 14));
        expectedInvoiceViewModel.setReturnDate(LocalDate.of(2021, 02, 15));
        expectedInvoiceViewModel.setLateFee(new BigDecimal("50.00"));

        Customer expectedCustomer = new Customer(
                1,
                "Tiani",
                "Edwards",
                "tiani@heckyeah.com",
                "Cognizant",
                "8675309"
        );
        expectedInvoiceViewModel.setCustomer(expectedCustomer);

        InvoiceItem invoiceItem = new InvoiceItem(
                1,
                1,
                1,
                1,
                new BigDecimal("1"),
                new BigDecimal("0.00")
        );

        List<InvoiceItem> expectedInvoiceItems = new ArrayList<InvoiceItem>(){{
           add(invoiceItem);
        }};
        expectedInvoiceViewModel.setItems(expectedInvoiceItems);

        List<InvoiceViewModel> expectedInvoiceViewModels = new ArrayList<InvoiceViewModel>(){{
            add(expectedInvoiceViewModel);
        }};

        List<InvoiceViewModel> resultOfRetrievingInvoices = service.findAllInvoices();

        assertEquals(expectedInvoiceViewModels, resultOfRetrievingInvoices);
    }

    private void setUpInvoiceItemDaoMock() {
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImpl.class);

        InvoiceItem invoiceItem = new InvoiceItem(
                1,
                1,
                1,
                1,
                new BigDecimal("1"),
                new BigDecimal("0.00")
        );
        InvoiceItem invoiceItem2 = new InvoiceItem(
                0,
                0,
                1,
                1,
                new BigDecimal("1"),
                new BigDecimal("0.00")
        );

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(invoiceItem);

        doReturn(invoiceItems).when(invoiceItemDao).getAllInvoiceItems();
        doReturn(invoiceItem).when(invoiceItemDao).addInvoiceItem(invoiceItem2);
        doReturn(invoiceItem).when(invoiceItemDao).getInvoiceItem(1);
        doReturn(invoiceItems).when(invoiceItemDao).getAllInvoiceItemsByInvoiceId(1);
    }

    private void setUpItemDaoMock() {
        itemDao = mock(ItemDaoJdbcTemplateImpl.class);
        Item item = new Item(
                1,
                "Deep Tissue Massager",
                "Massage all the knots out of everywhere",
                new BigDecimal("3.95")
        );
        Item item2 = new Item(
                0,
                "Deep Tissue Massager",
                "Massage all the knots out of everywhere",
                new BigDecimal("3.95")
        );
        List<Item> items = new ArrayList<>();
        items.add(item);

        doReturn(items).when(itemDao).getAllItems();
        doReturn(item).when(itemDao).addItem(item2);
        doReturn(item).when(itemDao).getItem(1);
    }

    private void setUpCustomerDaoMock() {
        customerDao = mock(CustomerDaoJdbcTemplateImpl.class);
        Customer customer = new Customer(
                1,
                "Tiani",
                "Edwards",
                "tiani@heckyeah.com",
                "Cognizant",
                "8675309"
        );
        Customer customer2 = new Customer(
                0,
                "Josh",
                "Shevach",
                "joshi@heckyeah.com",
                "Cognizant",
                "8675319"
        );

        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        doReturn(customers).when(customerDao).getAllCustomers();
        doReturn(customer).when(customerDao).addCustomer(customer2);
        doReturn(customer).when(customerDao).getCustomer(1);
    }

    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);
        Invoice invoice = new Invoice(
                1,
                1,
                LocalDate.of(2021, 02, 12),
                LocalDate.of(2021, 02, 14),
                LocalDate.of(2021, 02, 15),
                new BigDecimal("50.00")
        );
        Invoice invoice2 = new Invoice(
                0,
                1,
                LocalDate.of(2021, 02, 12),
                LocalDate.of(2021, 02, 14),
                LocalDate.of(2021, 02, 15),
                new BigDecimal("50.00")
        );

        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);

        doReturn(invoices).when(invoiceDao).getAllInvoices();
        doReturn(invoice).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getInvoice(1);

    }


}

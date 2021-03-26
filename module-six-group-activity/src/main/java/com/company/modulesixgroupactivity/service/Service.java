package com.company.modulesixgroupactivity.service;

import com.company.modulesixgroupactivity.dao.CustomerDao;
import com.company.modulesixgroupactivity.dao.InvoiceDao;
import com.company.modulesixgroupactivity.dao.InvoiceItemDao;
import com.company.modulesixgroupactivity.dao.ItemDao;
import com.company.modulesixgroupactivity.model.Customer;
import com.company.modulesixgroupactivity.model.Invoice;
import com.company.modulesixgroupactivity.model.InvoiceItem;
import com.company.modulesixgroupactivity.model.Item;
import com.company.modulesixgroupactivity.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class Service {

    private CustomerDao customerDao;
    private InvoiceDao invoiceDao;
    private ItemDao itemDao;
    private InvoiceItemDao invoiceItemDao;

    @Autowired
    public Service(CustomerDao customerDao, InvoiceDao invoiceDao, ItemDao itemDao, InvoiceItemDao invoiceItemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.itemDao = itemDao;
        this.invoiceItemDao = invoiceItemDao;
    }

    @Transactional
    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(invoiceViewModel.getCustomer().getCustomerId());
        invoice.setOrderDate(invoiceViewModel.getOrderDate());
        invoice.setPickupDate(invoiceViewModel.getPickupDate());
        invoice.setReturnDate(invoiceViewModel.getReturnDate());
        invoice.setLateFee(invoiceViewModel.getLateFee());
        invoice = invoiceDao.addInvoice(invoice);
        invoiceViewModel.setId(invoice.getInvoiceId());

        List<InvoiceItem> invoiceItems = invoiceViewModel.getItems();

        invoiceItems.forEach(item ->
        {
            item.setInvoiceItemId(invoiceViewModel.getId());
            invoiceItemDao.addInvoiceItem(item);
        });

        invoiceItems = invoiceItemDao.getAllInvoiceItemsByInvoiceId(invoiceViewModel.getId());
        invoiceViewModel.setItems(invoiceItems);

        return invoiceViewModel;

    }

    public InvoiceViewModel findInvoice(int id){
        Invoice invoice = invoiceDao.getInvoice(id);
        return buildInvoiceViewModel(invoice);
    }

    public List<InvoiceViewModel> findAllInvoices() {
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();

        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();

        for (Invoice invoice : invoiceList) {
            InvoiceViewModel invoiceViewModel = buildInvoiceViewModel(invoice);
            invoiceViewModelList.add(invoiceViewModel);
        }

        return invoiceViewModelList;
    }

    /*
    WILL COME BACK TO THIS
     */
//    @Transactional
//    public void updateInvoice(InvoiceViewModel invoiceViewModel) {
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceId(invoiceViewModel.getId());
//        invoice.setCustomerId(invoiceViewModel.getCustomer().getCustomerId());
//        invoice.setOrderDate(invoiceViewModel.getOrderDate());
//        invoice.setPickupDate(invoiceViewModel.getPickupDate());
//        invoice.setReturnDate(invoiceViewModel.getReturnDate());
//        invoice.setLateFee(invoiceViewModel.getLateFee());
//
//        //invoiceDao.updateInvoice(invoice);
//
//    }

    @Transactional
    public void removeInvoice(int id) {
        invoiceDao.deleteInvoice(id);
    }


    //Customers
    public Customer saveCustomer(Customer customer) {
        return customerDao.addCustomer(customer);
    }

    public Customer findCustomer(int id) {
        return customerDao.getCustomer(id);
    }

    public List<Customer> findAllCustomers() {
        return customerDao.getAllCustomers();
    }

    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    public void removeCustomer(int id) {
        customerDao.deleteCustomer(id);
    }


    //Items
    public Item saveItem(Item item) {
        return itemDao.addItem(item);
    }

    public Item findItem(int id) {
        return itemDao.getItem(id);
    }

    public List<Item> findAllItems() {
        return itemDao.getAllItems();
    }

    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    public void removeItem(int id) {
        itemDao.deleteItem(id);
    }

    //invoiceItems
    public InvoiceItem saveItem(InvoiceItem invoiceItem) {
        return invoiceItemDao.addInvoiceItem(invoiceItem);
    }

    public InvoiceItem findInvoiceItem(int id) {
        return invoiceItemDao.getInvoiceItem(id);
    }

    public List<InvoiceItem> findAllInvoiceItems() {
        return invoiceItemDao.getAllInvoiceItems();
    }

    public void removeInvoiceItem(int id) {
        invoiceItemDao.deleteInvoiceItem(id);
    }


    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice){
        Customer customer = customerDao.getCustomer(invoice.getCustomerId());

        List<InvoiceItem> invoiceItems = invoiceItemDao.getAllInvoiceItemsByInvoiceId(invoice.getInvoiceId());

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setId(invoice.getInvoiceId());
        invoiceViewModel.setCustomer(customer);
        invoiceViewModel.setItems(invoiceItems);
        invoiceViewModel.setOrderDate(invoice.getOrderDate());
        invoiceViewModel.setPickupDate(invoice.getPickupDate());
        invoiceViewModel.setReturnDate(invoice.getReturnDate());
        invoiceViewModel.setLateFee(invoice.getLateFee());

        return invoiceViewModel;
    }


}

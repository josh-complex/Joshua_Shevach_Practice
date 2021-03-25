package com.company.modulesixgroupactivity.dao;

import com.company.modulesixgroupactivity.model.Customer;
import com.company.modulesixgroupactivity.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice addInvoice(Invoice invoice);
    List<Invoice> getAllInvoices();
    List<Invoice> getInvoicesByCustomer(Customer customer);
    void deleteInvoice(int id);

}

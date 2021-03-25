package com.company.modulesixgroupactivity.dao;

import com.company.modulesixgroupactivity.model.InvoiceItem;

import java.util.List;

public interface InvoiceItemDao {

    InvoiceItem addInvoiceItem(InvoiceItem invoiceItem);
    InvoiceItem getInvoiceItem(int id);
    List<InvoiceItem> getAllInvoiceItems();
    void deleteInvoiceItem(int id);
    
}

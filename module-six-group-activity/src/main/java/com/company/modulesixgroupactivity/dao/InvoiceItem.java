package com.company.modulesixgroupactivity.dao;

import com.company.modulesixgroupactivity.model.Invoice;

import java.util.List;

public interface InvoiceItem {

    Invoice addInvoiceItem(InvoiceItem invoiceItem);
    InvoiceItem getInvoiceItem(int id);
    List<InvoiceItem> getAllInvoiceItems();
    void updateInvoiceItem(InvoiceItem invoiceItem);
    void deleteInvoiceItem(int id);
    
}

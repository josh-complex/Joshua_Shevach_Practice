package com.company.gamestore.dao;

import com.company.gamestore.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(long id);

    List<Invoice> getAllInvoices();

    List<Invoice> getAllInvoicesByCustomerName(String name);



    void deleteInvoice(long id);

}

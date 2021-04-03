package com.company.JoshuaShevachU1Capstone.controller;

import com.company.JoshuaShevachU1Capstone.model.Invoice;
import com.company.JoshuaShevachU1Capstone.service.GameStoreService;
import com.company.JoshuaShevachU1Capstone.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    GameStoreService service;

    @Autowired
    public InvoiceController(GameStoreService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel purchaseItems(@Valid @RequestBody Invoice invoice) {
        return service.addInvoice(invoice);
    }

}

package com.company.gamestoreservice.controller;

import com.company.gamestoreservice.model.Invoice;
import com.company.gamestoreservice.service.GameStoreService;
import com.company.gamestoreservice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/invoices")
@RefreshScope
@CrossOrigin(origins = "*")
public class InvoiceController {

    GameStoreService service;

    @Autowired
    public InvoiceController(GameStoreService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel purchaseItems(@Valid @RequestBody Invoice invoice) {
        return service.saveInvoice(invoice);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable Integer id) {
        return service.getInvoice(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getInvoices() {
        return service.getAllInvoices();
    }

}

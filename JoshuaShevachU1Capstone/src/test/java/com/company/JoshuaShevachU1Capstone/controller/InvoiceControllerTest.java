package com.company.JoshuaShevachU1Capstone.controller;

import com.company.JoshuaShevachU1Capstone.model.Invoice;
import com.company.JoshuaShevachU1Capstone.service.GameStoreService;
import com.company.JoshuaShevachU1Capstone.viewmodel.InvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GameStoreService service;

    private final ObjectMapper mapper = new ObjectMapper();

    private Invoice invoice;

    @Before
    public void setUp() {
        invoice = new Invoice();
        invoice.setName("Joshua Shevach");
        invoice.setCity("Orlando");
        invoice.setState("FL");
        invoice.setStreet("1110 Bassano Way");
        invoice.setZipcode("32828");
        invoice.setItemId(12);
        invoice.setItemType("Consoles");
        invoice.setQuantity(2);
    }

    @Test
    public void shouldAddInvoiceAndReturnInvoiceFromJson() throws Exception {
        String inputJson = mapper.writeValueAsString(invoice);

        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setName("Joshua Shevach");
        invoiceViewModel.setCity("Orlando");
        invoiceViewModel.setState("FL");
        invoiceViewModel.setStreet("1110 Bassano Way");
        invoiceViewModel.setZipcode("32828");

        String outputJson = mapper.writeValueAsString(invoiceViewModel);

        when(service.addInvoice(invoice)).thenReturn(invoiceViewModel);

        mockMvc.perform(post("/invoices")
        .content(inputJson)
        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

}

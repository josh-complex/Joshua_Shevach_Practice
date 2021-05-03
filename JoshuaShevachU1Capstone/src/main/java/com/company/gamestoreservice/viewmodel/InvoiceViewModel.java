package com.company.gamestoreservice.viewmodel;

import com.company.gamestoreservice.model.ProcessingFee;
import com.company.gamestoreservice.model.SalesTaxRate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceViewModel {

    private Integer invoiceId;
    private String name;
    private String street;
    private String city;
    private String state;
    private String zipcode;
    private String itemType;
    private Integer itemId;
    private BigDecimal unitPrice;
    private Integer quantity;
    private SalesTaxRate salesTaxRate;
    private ProcessingFee processingFee;
    private BigDecimal subtotal;
    private BigDecimal taxesAndFees;
    private BigDecimal total;

}

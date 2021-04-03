package com.company.JoshuaShevachU1Capstone.viewmodel;

import com.company.JoshuaShevachU1Capstone.model.Item;
import com.company.JoshuaShevachU1Capstone.model.ProcessingFee;
import com.company.JoshuaShevachU1Capstone.model.SalesTaxRate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceViewModel {

    @NotNull
    private int id;
    @NotNull
    @Size(max = 80)
    private String name;
    @NotNull
    @Size(max = 30)
    private String street;
    @NotNull
    @Size(max = 20)
    private String city;
    @NotNull
    @Size(max = 2)
    private String state;
    @NotNull
    @Size(max = 5)
    private String zipcode;
    @Positive
    private int quantity;
    @NotNull
    private Item item;
    @NotNull
    private SalesTaxRate salesTaxRate;
    @NotNull
    private ProcessingFee processingFee;
    @NotNull
    @Digits(integer = 5, fraction = 2)
    private BigDecimal subtotal;
    @NotNull
    @Digits(integer = 5, fraction = 2)
    private BigDecimal taxesAndFees;
    @NotNull
    @Digits(integer = 5, fraction = 2)
    private BigDecimal total;

}

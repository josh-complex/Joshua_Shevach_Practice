package com.company.JoshuaShevachU1Capstone.viewmodel;

import com.company.JoshuaShevachU1Capstone.model.Item;
import com.company.JoshuaShevachU1Capstone.model.ProcessingFee;
import com.company.JoshuaShevachU1Capstone.model.SalesTaxRate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceViewModel {

    @NotNull(message = "Must supply an ID")
    private int id;

    @NotBlank(message = "Must supply a name")
    @Size(max = 80, message = "Name must be 80 characters or less")
    private String name;

    @NotBlank(message = "Must supply a street")
    @Size(max = 30, message = "Street must be 30 characters or less")
    private String street;

    @NotBlank(message = "Must supply a city")
    @Size(max = 20, message = "City must be 20 characters or less")
    private String city;

    @NotBlank(message = "Must supply a state")
    @Size(min = 2, max = 2, message = "Must supply a valid 2-character state code")
    private String state;

    @NotBlank(message = "Must supply a zipcode")
    @Size(max = 5, message = "Must supply a valid 5-character zipcode")
    private String zipcode;

    @NotNull(message = "Must supply an quantity")
    @Positive(message = "Requested quantity cannot be zero")
    private int quantity;

    @NotNull(message = "Must supply an item")
    private Item item;

    @NotNull(message = "Must supply a sales tax rate")
    private SalesTaxRate salesTaxRate;

    @NotNull(message = "Must supply a processing fee")
    private ProcessingFee processingFee;

    @NotNull(message = "Must supply a subtotal")
    @Positive(message = "Subtotal must be a positive non-zero value")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    private BigDecimal subtotal;

    @NotNull(message = "Must supply taxes and fees")
    @Positive(message = "Taxes & fees must be a positive non-zero value")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    private BigDecimal taxesAndFees;

    @NotNull(message = "Must supply a total")
    @Positive(message = "Total must be a positive non-zero value")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    private BigDecimal total;

    public void setItem(Item item) {
        this.item = item;
    }

    public void setSalesTaxRate(SalesTaxRate salesTaxRate) {
        this.salesTaxRate = salesTaxRate;
    }

    public void setProcessingFee(ProcessingFee processingFee) {
        this.processingFee = processingFee;
    }

}

package com.company.gamestoreservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invoiceId;

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
    @Size(min = 5, max = 5, message = "Must supply a valid 5-character zipcode")
    private String zipcode;

    @NotBlank(message = "Must supply a item type")
    @Size(max = 50, message = "Item type must be 50 characters or less")
    private String itemType;

    @NotNull(message = "Must supply an item ID")
    private Integer itemId;

    @Positive(message = "Price must be a positive non-zero value")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    private BigDecimal unitPrice;

    @NotNull(message = "Must supply an quantity")
    @Positive(message = "Requested quantity cannot be zero")
    private Integer quantity;

    @Positive(message = "Subtotal must be a positive non-zero value")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    private BigDecimal subtotal;

    @Positive(message = "Tax must be a positive non-zero value")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    private BigDecimal tax;

    @Positive(message = "Processing fee must be a positive non-zero value")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    private BigDecimal processingFee;

    @Positive(message = "Total must be a positive non-zero value")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    private BigDecimal total;

}

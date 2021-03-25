package com.company.modulesixgroupactivity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {

    private int invoiceId;
    private int customerId;
    private Date orderDate;
    private Date pickupDate;
    private Date returnDate;
    private BigDecimal lateFee;

}

package com.company.modulesixgroupactivity.viewmodel;

import com.company.modulesixgroupactivity.model.Customer;
import com.company.modulesixgroupactivity.model.Invoice;
import com.company.modulesixgroupactivity.model.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceViewModel {

    private int id;
    private Customer customer;
    private Invoice invoice;
    private List<Item> item;
    private Date orderDate;
    private Date pickupDate;
    private Date returnDate;
    private BigDecimal lateFee;

}

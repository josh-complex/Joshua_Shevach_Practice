package com.company.modulesixgroupactivity.viewmodel;

import com.company.modulesixgroupactivity.model.Customer;
import com.company.modulesixgroupactivity.model.InvoiceItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceViewModel {

    private int id;
    private Customer customer;
    private List<InvoiceItem> items;
    private LocalDate orderDate;
    private LocalDate pickupDate;
    private LocalDate returnDate;
    private BigDecimal lateFee;

}

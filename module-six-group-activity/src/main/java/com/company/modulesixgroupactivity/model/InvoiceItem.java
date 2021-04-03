package com.company.modulesixgroupactivity.model;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceItem {

    private int invoiceItemId;
    private int invoiceId;
    private int itemId;
    private int quantity;
    @NotNull
    @Digits(integer = 8, fraction = 2)
    private BigDecimal unitRate;
    @NotNull
    @Digits(integer = 8, fraction = 2)
    private BigDecimal discount;

}

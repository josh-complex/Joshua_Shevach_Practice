package com.company.JoshuaShevachU1Capstone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@UniqueElements
public class ProcessingFee {

    @NotNull
    @Size(max = 20)
    private String productType;
    @NotNull
    @Digits(integer = 4, fraction = 2)
    private BigDecimal fee;

}

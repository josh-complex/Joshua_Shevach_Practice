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

    @NotBlank(message = "Must supply a product type")
    @Size(max = 20)
    private String productType;
    @NotNull(message = "Must supply a fee")
    @Positive(message = "Processing fee must be a positive non-zero value")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    private BigDecimal fee;

}

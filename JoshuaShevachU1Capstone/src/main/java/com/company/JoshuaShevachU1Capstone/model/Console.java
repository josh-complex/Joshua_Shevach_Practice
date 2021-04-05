package com.company.JoshuaShevachU1Capstone.model;

import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Console extends Item {

    @NotBlank(message = "Must supply a model")
    @Size(max = 50, message = "Model must be 50 characters or less")
    private String model;

    @NotBlank(message = "Must supply a manufacturer")
    @Size(max = 50, message = "Manufacturer must be 50 characters or less")
    private String manufacturer;

    @NotBlank(message = "Must supply a memory amount")
    @Size(max = 20, message = "Memory amount must be 20 characters or less")
    private String memoryAmount;

    @NotBlank(message = "Must supply a processor")
    @Size(max = 20, message = "Processor must be 20 characters or less")
    private String processor;

    public Console(int id, String model, String manufacturer, String memoryAmount, String processor, BigDecimal price, int quantity) {
        super(id, quantity, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.memoryAmount = memoryAmount;
        this.processor = processor;
    }
}

package com.company.JoshuaShevachU1Capstone.model;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Console extends Item {

    @NotNull
    @Size(max = 50)
    private String model;
    @NotNull
    @Size(max = 50)
    private String manufacturer;
    @Size(max = 20)
    private String memoryAmount;
    @Size(max = 20)
    private String processor;

    public Console(int id, String model, String manufacturer, String memoryAmount, String processor, BigDecimal price, int quantity) {
        super(id, "Consoles", quantity, price);
        this.model = model;
        this.manufacturer = manufacturer;
        this.memoryAmount = memoryAmount;
        this.processor = processor;
    }
}

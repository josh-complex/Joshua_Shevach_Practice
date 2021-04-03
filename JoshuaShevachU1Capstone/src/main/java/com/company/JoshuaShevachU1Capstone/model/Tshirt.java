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
public class Tshirt extends Item {

    @NotNull
    @Size(max = 20)
    private String size;
    @NotNull
    @Size(max = 20)
    private String color;
    @NotNull
    @Size(max = 255)
    private String description;


    public Tshirt(int id, String size, String color, String description, BigDecimal price, int quantity) {
        super(id, "T-Shirts", quantity, price);
        this.size = size;
        this.color = color;
        this.description = description;
    }
}

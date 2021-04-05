package com.company.JoshuaShevachU1Capstone.model;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tshirt extends Item {

    @NotBlank(message = "Must supply a size")
    @Size(max = 20, message = "Size must be 20 characters or less")
    private String size;

    @NotBlank(message = "Must supply a color")
    @Size(max = 20, message = "Color must be 20 characters or less")
    private String color;

    @NotBlank(message = "Must supply a description")
    @Size(max = 255, message = "Description must be 255 characters or less")
    private String description;

    public Tshirt(int id, String size, String color, String description, BigDecimal price, int quantity) {
        super(id, "T-Shirts", quantity, price);
        this.size = size;
        this.color = color;
        this.description = description;
    }
}

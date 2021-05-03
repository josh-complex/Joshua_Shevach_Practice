package com.company.gamestoreservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="tShirt")
public class Tshirt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer tShirtId;

    @NotNull(message = "Must supply a quantity")
    @Positive(message = "Quantity must be greater than 0")
    protected Integer quantity;

    @NotNull(message = "Must supply a price")
    @Positive(message = "Price must be greater than 0")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    protected BigDecimal price;

    @NotBlank(message = "Must supply a size")
    @Size(max = 20, message = "Size must be 20 characters or less")
    private String size;

    @NotBlank(message = "Must supply a color")
    @Size(max = 20, message = "Color must be 20 characters or less")
    private String color;

    @NotBlank(message = "Must supply a description")
    @Size(max = 255, message = "Description must be 255 characters or less")
    private String description;

}

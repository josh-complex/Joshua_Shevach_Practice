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

    private final String itemType = "T-shirts";
    private final String image = "https://source.unsplash.com/random/200x250";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tShirtId;

    @NotNull(message = "Must supply a name")
    @Size(max = 50, message = "Name must be 50 characters or less")
    private String name;

    @NotBlank(message = "Must supply a description")
    @Size(max = 255, message = "Description must be 255 characters or less")
    private String description;

    @NotBlank(message = "Must supply a size")
    @Size(max = 20, message = "Size must be 20 characters or less")
    private String size;

    @NotBlank(message = "Must supply a color")
    @Size(max = 20, message = "Color must be 20 characters or less")
    private String color;

    @NotNull(message = "Must supply a price")
    @Positive(message = "Price must be greater than 0")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    private BigDecimal price;

    @NotNull(message = "Must supply a quantity")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

}

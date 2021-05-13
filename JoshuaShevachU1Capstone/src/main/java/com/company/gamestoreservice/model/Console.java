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
@Table(name="console")
public class Console {

    private final String itemType = "Consoles";
    private final String image = "https://source.unsplash.com/random/200x250";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer consoleId;

    @NotNull(message = "Must supply a name")
    @Size(max = 50, message = "Name must be 50 characters or less")
    private String name;

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

    @NotNull(message = "Must supply a price")
    @Positive(message = "Price must be greater than 0")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    private BigDecimal price;

    @NotNull(message = "Must supply a quantity")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

}

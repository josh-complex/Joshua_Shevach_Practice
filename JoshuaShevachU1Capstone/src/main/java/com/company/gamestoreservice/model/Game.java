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
@Table(name="game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer gameId;

    @NotNull(message = "Must supply a quantity")
    @Positive(message = "Quantity must be greater than 0")
    protected Integer quantity;

    @NotNull(message = "Must supply a price")
    @Positive(message = "Price must be greater than 0")
    @Digits(integer = 5, fraction = 2, message = "Value must be less than $99,999.99")
    protected BigDecimal price;

    @NotBlank(message = "Must supply a title")
    @Size(max = 50, message = "Title must be 50 characters or less")
    private String title;

    @NotBlank(message = "Must supply an ESRB rating")
    @Size(max = 50, message = "ESRB Rating must be 50 characters or less")
    private String esrbRating;

    @NotBlank(message = "Must supply a description")
    @Size(max = 255, message = "Description must be 255 characters or less")
    private String description;

    @NotBlank(message = "Must supply a studio")
    @Size(max = 50, message = "Studio must be 50 characters or less")
    private String studio;

}

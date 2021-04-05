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
public class Game extends Item{

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

    public Game(int id, String title, String esrbRating, String description, BigDecimal price, String studio, int quantity) {
        super(id, quantity, price);
        this.title = title;
        this.esrbRating = esrbRating;
        this.description = description;
        this.studio = studio;
    }
}

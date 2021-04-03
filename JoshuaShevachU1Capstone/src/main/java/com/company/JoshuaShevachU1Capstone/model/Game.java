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
public class Game extends Item{

    @NotNull
    @Size(max = 50)
    private String title;
    @NotNull
    @Size(max = 50)
    private String esrbRating;
    @NotNull
    @Size(max = 255)
    private String description;
    @NotNull
    @Size(max = 50)
    private String studio;

    public Game(int id, String title, String esrbRating, String description, BigDecimal price, String studio, int quantity) {
        super(id, "Games", quantity, price);
        this.title = title;
        this.esrbRating = esrbRating;
        this.description = description;
        this.studio = studio;
    }
}

package com.company.U1M5ChallengeLastnameFirstname.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private int id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @Size(max = 2)
    @NotNull
    private String state;
    @NotNull
    private String postalCode;
    @NotNull
    private String phone;
    @NotNull
    private String email;

}

package com.company.U1M5ChallengeLastnameFirstname.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    private int id;
    @NotNull
    @Size(max = 70)
    private String firstName;
    @NotNull
    @Size(max = 70)
    private String lastName;
    @NotNull
    @Size(max = 70)
    private String street;
    @NotNull
    @Size(max = 70)
    private String city;
    @NotNull
    @Size(max = 2)
    private String state;
    @NotNull
    @Size(max = 25)
    private String postalCode;
    @NotNull
    @Size(max = 15)
    private String phone;
    @NotNull
    @Email
    @Size(max = 60)
    private String email;

}

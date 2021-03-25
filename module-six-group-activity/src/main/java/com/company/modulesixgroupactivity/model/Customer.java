package com.company.modulesixgroupactivity.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private String phone;

}





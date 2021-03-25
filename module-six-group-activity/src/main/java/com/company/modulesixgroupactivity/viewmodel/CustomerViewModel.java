package com.company.modulesixgroupactivity.viewmodel;

import com.company.modulesixgroupactivity.model.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerViewModel {

    private int id;
    private List<Invoice> invoices;
    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private String phone;

}

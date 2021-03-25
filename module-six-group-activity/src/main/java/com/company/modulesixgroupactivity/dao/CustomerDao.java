package com.company.modulesixgroupactivity.dao;

import com.company.modulesixgroupactivity.model.Customer;

import java.util.List;

public interface CustomerDao {

    Customer addCustomer(Customer customer);
    Customer getCustomer(int id);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);

}

package com.company.modulesixgroupactivity.dao;

import com.company.modulesixgroupactivity.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoTest {

    @Autowired
    CustomerDao customerDao;

    @Before
    public void setUp() throws Exception {
        List<Customer> customerList = customerDao.getAllCustomers();

        for (Customer itr : customerList)
            customerDao.deleteCustomer(itr.getCustomerId());
    }

    @Test
    public void shouldAddGetDeleteCustomer() {
        Customer customer = new Customer(
                0,
                "Tiani",
                "Edwards",
                "tiani@heckyeah.com",
                "Cognizant",
                "8675309"
        );

        customer = customerDao.addCustomer(customer);

        Customer customer2 = customerDao.getCustomer(customer.getCustomerId());

        assertEquals(customer2, customer);

        customerDao.deleteCustomer(customer.getCustomerId());

        customer2 = customerDao.getCustomer(customer.getCustomerId());

        assertNull(customer2);

    }

    @Test
    public void shouldGetAllCustomers() {
        Customer customer = new Customer(
                0,
                "Tiani",
                "Edwards",
                "tiani@heckyeah.com",
                "Cognizant",
                "8675309"
        );

        customer = customerDao.addCustomer(customer);

        assertEquals(1, customerDao.getAllCustomers().size());

        customerDao.deleteCustomer(customer.getCustomerId());

        assertEquals(0, customerDao.getAllCustomers().size());
    }

    @Test
    public void shouldUpdateCustomer() {

        Customer customer = new Customer(
                0,
                "Tiani",
                "Edwards",
                "tiani@heckyeah.com",
                "Cognizant",
                "8675309"
        );

        //This now has the incremented id
        customer = customerDao.addCustomer(customer);

        customer.setEmail("tiani@wowzers.org");
        customer.setCompany("Google");

        //The customer we pass through now has the proper id AS WELL AS the new email and company info
        customerDao.updateCustomer(customer);

        Customer customer2 = customerDao.getCustomer(customer.getCustomerId());

    }
}
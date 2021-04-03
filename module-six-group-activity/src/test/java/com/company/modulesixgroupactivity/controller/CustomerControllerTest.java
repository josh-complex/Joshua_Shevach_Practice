package com.company.modulesixgroupactivity.controller;

import com.company.modulesixgroupactivity.dao.CustomerDaoJdbcTemplateImpl;
import com.company.modulesixgroupactivity.model.Customer;
import com.company.modulesixgroupactivity.service.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.HttpClientErrorException;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @MockBean
    private Service service;

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper mapper = new ObjectMapper();


    String inputJson;
    String outputJson;

    Customer customer = new Customer(
            1,
            "Tiani",
            "Edwards",
            "tiani@heckyeah.com",
            "Cognizant",
            "8675309"
    );
    Customer customer2 = new Customer(
            0,
            "Josh",
            "Shevach",
            "joshi@heckyeah.com",
            "Cognizant",
            "8675319"
    );
    private List<Customer> customers = new ArrayList<Customer>(){{
        add(customer);
        add(customer2);
    }};

    @Before
    public void setUp() throws Exception {
        setUpServiceMock();
    }

    @Test
    public void shouldReturnAllCustomersInCollection() throws Exception {

        String outputJson = mapper.writeValueAsString(customers);

        mockMvc.perform(get("/customer"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNewCustomerOnPost() throws Exception {

        mockMvc.perform(post("/customer")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void shouldReturn422StatusCodeWithInvalidRequestBody() throws Exception {

        customer2.setEmail("");
        inputJson = mapper.writeValueAsString(customer2);

        System.out.println(inputJson);

        mockMvc.perform(post("/customer")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    private void setUpServiceMock() throws Exception {

        inputJson = mapper.writeValueAsString(customer2);
        outputJson = mapper.writeValueAsString(customer);

        doReturn(customers).when(service).findAllCustomers();
        doReturn(customer).when(service).saveCustomer(customer2);
        doReturn(customer).when(service).findCustomer(1);
    }

}

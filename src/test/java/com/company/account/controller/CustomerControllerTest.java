package com.company.account.controller;

import com.company.account.IntegrationTestSupport;
import com.company.account.dto.CustomerDto;
import com.company.account.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Objects;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends IntegrationTestSupport {

    @Test
    public void testGetCustomerById_whenCustomerIdExists_shouldReturnCustomerDto() throws Exception {

        Customer customer = customerRepository.save(generateCustomer());
        accountService.createAccount(generateCreateAccountRequest(customer.getId(), 100));

        CustomerDto expected = converter.convertToCustomerDto(
                customerRepository.getById(
                        Objects.requireNonNull(customer.getId())));

        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + customer.getId()))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(mapper.writer().withDefaultPrettyPrinter().writeValueAsString(expected), false))
                .andReturn();
    }

    @Test
    public void testGetCustomerById_whenCustomerIdDoesNotExist_shouldReturnHttpNotFound() throws Exception {

        this.mockMvc.perform(get(CUSTOMER_API_ENDPOINT + "non-exists-customer"))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}

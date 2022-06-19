package com.company.account.service;

import com.company.account.dto.CustomerDto;
import com.company.account.dto.CustomerDtoConverter;
import com.company.account.exception.CustomerNotFoundException;
import com.company.account.model.Customer;
import com.company.account.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    protected Customer findCustomerById(String id){
        return customerRepository.findById(id)
                .orElseThrow(
                        ()-> new CustomerNotFoundException("Customer could not find by id: " + id));
    }

    public CustomerDto getCustomerById(String customerId) {
        return converter.convertToCustomerDto(findCustomerById(customerId));
    }

    public List<CustomerDto> getAllCustomer() {

        return customerRepository.findAll()
                .stream()
                .map(converter::convertToCustomerDto)
                .collect(Collectors.toList());
    }
}

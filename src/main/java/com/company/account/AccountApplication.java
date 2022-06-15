package com.company.account;

import com.company.account.model.Customer;
import com.company.account.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {

    private final CustomerRepository customerRepository;

    public AccountApplication(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = customerRepository.save(new Customer("Parvin","Valizade"));

        System.out.println(customer);
    }
}

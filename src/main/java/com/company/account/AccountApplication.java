package com.company.account;

import com.company.account.exception.RestTemplateResponseErrorHandler;
import com.company.account.model.Customer;
import com.company.account.repository.CustomerRepository;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final RestTemplateResponseErrorHandler responseErrorHandler;

    public AccountApplication(CustomerRepository customerRepository, RestTemplateResponseErrorHandler responseErrorHandler) {
        this.customerRepository = customerRepository;
        this.responseErrorHandler = responseErrorHandler;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String description,
                                 @Value("${application-version}") String version){
        return new OpenAPI()
                .info(new Info()
                        .title("Account API")
                        .version(version)
                        .description(description)
                        .license(new License().name("Account API Licence")));
    }
    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder().errorHandler(responseErrorHandler).build();
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = customerRepository.save(new Customer("Parvin","Valizade"));

        System.out.println(customer);
    }
}

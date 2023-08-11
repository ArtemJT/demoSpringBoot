package com.example.demospringboot.service.impl;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.repository.CustomerRepository;
import com.example.demospringboot.service.interfaces.LoaderService;
import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Slf4j
@AllArgsConstructor
@Service
public class LoaderServiceBean implements LoaderService {

    private final CustomerRepository customerRepository;

    @Override
    public void generateCustomers(Integer qty) {
        List<Customer> customers = createCustomersList(qty);
        customerRepository.saveAll(customers);
    }

    @Override
    public long countCustomers() {
        return customerRepository.count();
    }

    private List<Customer> createCustomersList(Integer qty) {

        List<Customer> customerList = new ArrayList<>();
        long seed = 1;

        Faker faker = new Faker(new Locale("en"), new Random(seed));
        for (int i = 0; i < qty; i++) {
            String name = faker.name().name();
            String login = faker.name().username();
            String email = login.replace('.', '_') + "@mail.com";

            Customer manager = Customer.builder()
                    .login(login)
                    .name(name)
                    .email(email)
                    .build();
            customerList.add(manager);
        }

        return customerList;
    }

}

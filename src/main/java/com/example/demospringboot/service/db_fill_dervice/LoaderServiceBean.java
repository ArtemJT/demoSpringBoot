package com.example.demospringboot.service.db_fill_dervice;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.repository.CustomerRepository;
import com.example.demospringboot.repository.ManagerRepository;
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

    private final ManagerRepository managerRepository;
    private final CustomerRepository customerRepository;

    /**
     *
     */
    @Override
    public void generateData(Integer qty) {
        List<Manager> managers = createManagersList(qty);
        managerRepository.saveAll(managers);
        List<Customer> customers = createCustomersList(qty);
        customerRepository.saveAll(customers);
    }

    /**
     * @return
     */
    @Override
    public long count() {
        return customerRepository.count();
    }

    private List<Manager> createManagersList(Integer qty) {

        List<Manager> managerList = new ArrayList<>();
        long seed = 1;

        Faker faker = new Faker(new Locale("en"), new Random(seed));
        for (int i = 0; i < qty; i++) {
            String name = faker.name().name();
            String dept = faker.commerce().department();

            Manager manager = Manager.builder()
                    .name(name)
                    .department(dept)
                    .build();
            managerList.add(manager);
        }

        return managerList;
    }

    private List<Customer> createCustomersList(Integer qty) {

        List<Customer> customerList = new ArrayList<>();
        long seed = 1;

        Faker faker = new Faker(new Locale("en"), new Random(seed));
        for (int i = 0; i < qty; i++) {
            String name = faker.name().name();
            String login = faker.name().username();
            String email = faker.name().username() + "@mail.com";

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

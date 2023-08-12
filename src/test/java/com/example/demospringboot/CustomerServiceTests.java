package com.example.demospringboot;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.repository.CustomerRepository;
import com.example.demospringboot.service.impl.CustomerServiceBean;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * @author Artem Kovalov on 12.08.2023
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Customer Service Tests")
class CustomerServiceTests {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceBean service;

    private Customer customer;
    private List<Customer> customers;

    @Test
    @DisplayName("Create customer test")
    void createTest() {
        doReturn(customer).when(customerRepository).save(any(Customer.class));

        var created = service.create(customer);
        assertThat(created.getName()).isSameAs(customer.getName());
        verify(customerRepository).save(customer);
    }

    @Test
    @DisplayName("Get customer by id")
    void getByIdTest() {
        doReturn(Optional.of(customer)).when(customerRepository).findById(1);
        doThrow(EntityNotFoundException.class).when(customerRepository).findById(2);

        var expected = service.getById(customer.getId());

        assertNotNull(expected);
        assertThat(expected).isSameAs(customer);
        assertThrows(EntityNotFoundException.class, () -> service.getById(2));

        verify(customerRepository).findById(customer.getId());
    }

    @Test
    @DisplayName("Get all customers")
    void getAllTest() {
        doReturn(customers).when(customerRepository).findAll();

        var list = customerRepository.findAll();

        assertThat(list).isNotEmpty();
        verify(customerRepository).findAll();
    }

    @BeforeEach
    void setUp() {
        customer = Customer.builder()
                .id(1)
                .name("Customer")
                .email("customer_1@mail.com")
                .login("customer_login")
                .isDeleted(false)
                .build();

        customers = List.of(
                Customer.builder()
                        .id(2)
                        .name("Second Customer")
                        .email("customer_2@mail.com")
                        .login("customer_2")
                        .isDeleted(false)
                        .build(),
                Customer.builder()
                        .id(3)
                        .name("Deleted")
                        .email("deleted@mail.com")
                        .login("is(deleted")
                        .isDeleted(true)
                        .build()
        );
    }
}

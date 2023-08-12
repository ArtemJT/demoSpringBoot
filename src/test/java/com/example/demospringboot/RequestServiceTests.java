package com.example.demospringboot;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.Request;
import com.example.demospringboot.domain.RequestStatus;
import com.example.demospringboot.repository.RequestRepository;
import com.example.demospringboot.service.impl.RequestServiceBean;
import com.example.demospringboot.service.interfaces.CustomerService;
import com.example.demospringboot.util.exception.RequestNotAssignException;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * @author Artem Kovalov on 12.08.2023
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Request Service Tests")
class RequestServiceTests {

    @Mock
    private RequestRepository requestRepository;

    @InjectMocks
    private RequestServiceBean service;

    @Mock
    private CustomerService customerService;

    @Mock
    private RequestStatus requestStatus;

    private Request request;
    private List<Request> requests;

    @Test
    @DisplayName("Get request by id")
    void getByIdTest() {
        doReturn(Optional.of(request)).when(requestRepository).findById(1);
        doThrow(EntityNotFoundException.class).when(requestRepository).findById(2);

        var expected = service.getById(request.getId());

        assertNotNull(expected);
        assertThat(expected).isSameAs(request);
        assertThrows(EntityNotFoundException.class, () -> service.getById(2));

        verify(requestRepository).findById(request.getId());
    }

    @Test
    @DisplayName("Get all requests")
    void getAllTest() {
        doReturn(requests).when(requestRepository).findAll();

        var list = requestRepository.findAll();

        assertThat(list).isNotEmpty();
        verify(requestRepository).findAll();
    }

    @Test
    @DisplayName("Complete request")
    void completeRequest() {
        doReturn(Optional.of(request)).when(requestRepository).findById(1);

        assertThrows(RequestNotAssignException.class, () -> service.completeRequest(1));
    }

    @Test
    @DisplayName("Get all requests by customer id")
    void getAllRequestsByCustomerId() {
        Customer customer = Customer.builder().name("Customer").requests(new HashSet<>(requests)).build();
        when(customerService.getById(anyInt())).thenReturn(customer);
        doReturn(requests).when(requestRepository).findAllByCustomer(customer);

        var expected = service.getAllRequestsByCustomerId(anyInt());
        assertNotNull(expected);
        assertThat(expected.get(0).getCreationDate()).isEqualTo(requests.get(0).getCreationDate());
    }

    @BeforeEach
    void setUp() {
        request = Request.builder()
                .id(1)
                .status(requestStatus)
                .build();

        requests = List.of(
                request,
                Request.builder()
                        .id(1)
                        .status(requestStatus)
                        .build(),
                Request.builder()
                        .id(1)
                        .status(requestStatus)
                        .build()
        );
    }
}

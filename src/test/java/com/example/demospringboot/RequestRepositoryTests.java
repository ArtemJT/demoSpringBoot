package com.example.demospringboot;

import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.domain.Request;
import com.example.demospringboot.domain.RequestStatus;
import com.example.demospringboot.repository.RequestRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Artem Kovalov on 12.08.2023
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Booking Repository Tests")
class RequestRepositoryTests {

    @Autowired
    private RequestRepository repository;

    private static final Manager MANAGER = Manager.builder().id(1).name("Manager").build();

    @Test
    @Order(1)
    @Rollback(value = false)
    @DisplayName("Save request")
    void saveRequestTest() {
        var request = Request.builder()
                .id(1)
                .status(RequestStatus.IN_PROGRESS)
                .manager(MANAGER)
                .build();

        repository.save(request);

        assertThat(request.getId()).isNotNull();
        assertThat(request.getId()).isPositive();
        assertThat(request.getStatus()).isEqualTo(RequestStatus.IN_PROGRESS);
    }

    @Test
    @Order(2)
    @DisplayName("Get request by id")
    void getRequestByIdTest() {

        var request = repository.findById(1).orElseThrow();

        assertThat(request.getId()).isNotNull();
        assertThat(request.getId()).isOdd();
        assertThat(request.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    @DisplayName("Get all requests")
    void getAllRequestsTest() {

        var list = repository.findAll();

        assertThat(list).isNotNull().isNotEmpty();
    }

    @Test
    @Order(4)
    @DisplayName("Get all requests by manager")
    void getAllRequestsByManagerTest() {

        var list = repository.findAllByManager(MANAGER);

        assertThat(list).isNotNull().isNotEmpty();
        assertThat(list.get(0).getStatus()).isEqualTo(RequestStatus.IN_PROGRESS);
    }
}

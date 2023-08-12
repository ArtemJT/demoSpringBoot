package com.example.demospringboot.repository;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.domain.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Artem Kovalov on 09.08.2023
 */
@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    List<Request> findAllByCustomer(Customer customer);

    List<Request> findAllByManager(Manager manager);
}

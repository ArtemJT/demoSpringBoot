package com.example.demospringboot.repository;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Artem Kovalov on 09.08.2023
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByCustomer(Customer customer);

    List<Order> findAllByManager(Manager manager);
}

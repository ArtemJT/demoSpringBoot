package com.example.demospringboot.repository;

import com.example.demospringboot.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Artem Kovalov on 09.08.2023
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}

package com.example.demospringboot.repository;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Artem Kovalov on 09.08.2023
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

    List<Booking> findAllByCustomer(Customer customer);

    List<Booking> findAllByManager(Manager manager);
}

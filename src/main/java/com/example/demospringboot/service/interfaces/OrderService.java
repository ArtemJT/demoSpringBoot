package com.example.demospringboot.service.interfaces;

import com.example.demospringboot.domain.Order;

import java.util.List;

/**
 * @author Artem Kovalov on 10.08.2023
 */
public interface OrderService {

    // TODO Add Customer as parameter
    Order create();

    Order getById(Integer id);

    List<Order> getAll();

    Order completeOrder(Integer id);

    Order declineOrder(Integer id);
}

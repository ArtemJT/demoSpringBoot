package com.example.demospringboot.service.interfaces;

public interface LoaderService {

    /**
     * Generating and saving Customers into DB
     * @param qty amount of record that need to save
     */
    void generateCustomers(Integer qty);

    /**
     * Counting Customer records in DB
     * @return amount of records
     */
    long countCustomers();
}

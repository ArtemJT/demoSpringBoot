package com.example.demospringboot.service.interfaces;

import com.example.demospringboot.domain.Manager;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

/**
 * @author Artem Kovalov on 10.08.2023
 */
public interface ManagerService {

    /**
     * Saving record Manager into DB
     *
     * @param manager passed from JSON-request
     * @return object Manager if saving successful
     */
    Manager create(Manager manager);

    /**
     * Finding record by ID
     *
     * @param id Manager id
     * @return Manager which found in DB
     * @throws EntityNotFoundException if object not found
     */
    Manager getById(Integer id) throws EntityNotFoundException;

    /**
     * Finding all records
     *
     * @return list of records
     */
    List<Manager> getAll();

    /**
     * Marking record as 'deleted'
     *
     * @param id Manager id
     * @return marked record if successful
     * @throws EntityNotFoundException if object not found
     */
    Manager deleteById(Integer id) throws EntityNotFoundException;
}

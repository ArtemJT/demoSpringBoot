package com.example.demospringboot.repository;

import com.example.demospringboot.domain.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Artem Kovalov on 09.08.2023
 */
@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}

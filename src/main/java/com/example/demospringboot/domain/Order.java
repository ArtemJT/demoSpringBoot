package com.example.demospringboot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Artem Kovalov on 08.08.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "bookings")
public final class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @Builder.Default
    private String uuid = UUID.randomUUID().toString();

    private LocalDateTime creationDate;

    @Column(nullable = false)
    private OrderStatus status;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Manager manager;
}

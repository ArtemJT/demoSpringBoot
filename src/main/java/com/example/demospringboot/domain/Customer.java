package com.example.demospringboot.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

/**
 * @author Artem Kovalov on 08.08.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
@Table(name = "clients")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Booking> bookings;

}

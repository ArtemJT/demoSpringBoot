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
@Table(name = "managers")
public final class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String department;

    @Builder.Default
    private Boolean isDeleted = Boolean.FALSE;

    @OneToMany
    @JoinColumn(name = "manager_id")
    private Set<Order> orders;
}

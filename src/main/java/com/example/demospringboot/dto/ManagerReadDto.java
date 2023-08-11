package com.example.demospringboot.dto;

import lombok.Builder;


/**
 * @author Artem Kovalov on 11.08.2023
 */
@Builder
public record ManagerReadDto(Integer id, String name, String email) {

}

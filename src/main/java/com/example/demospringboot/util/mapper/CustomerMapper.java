package com.example.demospringboot.util.mapper;

import com.example.demospringboot.domain.Customer;
import com.example.demospringboot.dto.CustomerDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto toDto(Customer entity);

    @InheritInverseConfiguration
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "requests", ignore = true)
    Customer toEntity(CustomerDto dto);

    List<CustomerDto> toDtoCol(List<Customer> entityCol);

}

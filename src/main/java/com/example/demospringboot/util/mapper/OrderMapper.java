package com.example.demospringboot.util.mapper;

import com.example.demospringboot.domain.Order;
import com.example.demospringboot.dto.OrderDto;
import com.example.demospringboot.dto.OrderReadDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderReadDto toReadDto(Order order);

    OrderDto toDto(Order order);

    @InheritInverseConfiguration
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    Order toEntity(OrderDto orderDto);

    List<OrderReadDto> toReadDtoCol(List<Order> orderList);

}

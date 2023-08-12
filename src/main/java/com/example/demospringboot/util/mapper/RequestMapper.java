package com.example.demospringboot.util.mapper;

import com.example.demospringboot.domain.Request;
import com.example.demospringboot.dto.RequestFullDto;
import com.example.demospringboot.dto.RequestReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@Mapper(componentModel = "spring")
public interface RequestMapper {
    RequestMapper INSTANCE = Mappers.getMapper(RequestMapper.class);

    RequestFullDto toFullDto(Request entity);

    RequestReadDto toReadDto(Request entity);

    List<RequestReadDto> toReadDtoCol(List<Request> entityList);

}

package com.example.demospringboot.util.mapper;

import com.example.demospringboot.domain.Manager;
import com.example.demospringboot.dto.ManagerDto;
import com.example.demospringboot.dto.ManagerReadDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@Mapper(componentModel = "spring")
public interface ManagerMapper {
    ManagerMapper INSTANCE = Mappers.getMapper(ManagerMapper.class);

    ManagerReadDto toReadDto(Manager entity);

    ManagerDto toDto(Manager entity);

    @InheritInverseConfiguration
    @Mapping(target = "isDeleted", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Manager toEntity(ManagerDto dto);

    List<ManagerReadDto> toReadDtoCol(List<Manager> entityCol);

}

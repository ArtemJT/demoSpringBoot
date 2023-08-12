package com.example.demospringboot.util.mapper;

import com.example.demospringboot.domain.Booking;
import com.example.demospringboot.dto.BookingDto;
import com.example.demospringboot.dto.BookingReadDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingReadDto toReadDto(Booking booking);

    BookingDto toDto(Booking booking);

    @InheritInverseConfiguration
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "status", ignore = true)
    Booking toEntity(BookingDto bookingDto);

    List<BookingReadDto> toReadDtoCol(List<Booking> bookingList);

}

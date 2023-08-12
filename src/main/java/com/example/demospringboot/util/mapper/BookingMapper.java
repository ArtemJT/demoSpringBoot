package com.example.demospringboot.util.mapper;

import com.example.demospringboot.domain.Booking;
import com.example.demospringboot.dto.BookingFullDto;
import com.example.demospringboot.dto.BookingReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Artem Kovalov on 11.08.2023
 */
@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingFullDto toFullDto(Booking booking);

    BookingReadDto toReadDto(Booking booking);

    List<BookingReadDto> toReadDtoCol(List<Booking> bookingList);

}

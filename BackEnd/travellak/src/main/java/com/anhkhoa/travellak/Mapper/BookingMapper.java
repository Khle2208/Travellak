package com.anhkhoa.travellak.Mapper;

import com.anhkhoa.travellak.Entity.Booking;
import com.anhkhoa.travellak.dto.Request.Booking.BookingCreationRequest;
import com.anhkhoa.travellak.dto.Request.Booking.BookingUpdateRequest;
import com.anhkhoa.travellak.dto.Response.BookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "bookingId", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "bookingDate", ignore = true)
    Booking toBooking(BookingCreationRequest request);
    BookingResponse toBookingResponse(Booking booking);
    List<BookingResponse> toListBookingResponse(List<Booking> bookings);

    @Mapping(target = "bookingId", ignore = true)
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "bookingDate", ignore = true)
    void updateBooking(@MappingTarget Booking booking, BookingUpdateRequest request);

}

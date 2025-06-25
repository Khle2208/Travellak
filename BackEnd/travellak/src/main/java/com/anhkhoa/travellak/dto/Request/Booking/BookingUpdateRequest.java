package com.anhkhoa.travellak.dto.Request.Booking;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingUpdateRequest {
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate endDate;

    int numberAdult;
    int numberChild;
    int numberBaby;
    UUID tourId;
    UUID userId;
    Double totalPrice;
    String status;
}

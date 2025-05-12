package com.anhkhoa.travellak.dto.Request.Booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingCreationRequest {
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate endDate;
    int numberAdult;
    int numberChild;
    int numberBaby;
    UUID userId;
    UUID tourId;
    Double totalPrice;
    String status = "Chờ thanh toán";
}

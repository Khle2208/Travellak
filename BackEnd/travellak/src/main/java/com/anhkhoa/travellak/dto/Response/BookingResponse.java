package com.anhkhoa.travellak.dto.Response;

import java.time.LocalDate;
import java.util.UUID;

import com.anhkhoa.travellak.Entity.Tour;
import com.anhkhoa.travellak.Entity.Users;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingResponse {
    UUID bookingId;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate bookingDate;
    int numberAdult;
    int numberChild;
    int numberBaby;
    Tour tour;
    Users user;
    Double totalPrice;
    String status;
}

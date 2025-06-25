package com.anhkhoa.travellak.Entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID bookingId;

    LocalDate startDate;
    LocalDate endDate;
    LocalDate bookingDate;
    int numberAdult;
    int numberChild;
    int numberBaby;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    Users user;

    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    Tour tour;

    Double totalPrice;

    @Column(columnDefinition = "NVARCHAR(100)")
    String status;
}

package com.anhkhoa.travellak.dto.Response;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

import com.anhkhoa.travellak.Entity.Cities;
import com.anhkhoa.travellak.Entity.DayTour;
import com.anhkhoa.travellak.Entity.TourImage;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourResponse {
    UUID tourId;
    String tourName;
    String description;
    int rating;
    double priceAdult;
    double priceChild;
    double priceBaby;
    byte[] imageData;
    Cities departureCity;
    Cities destinationCity;
    List<DayTour> dayTours;
    private List<TourImage> tourImages;
}

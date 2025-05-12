package com.anhkhoa.travellak.dto.Response;

import com.anhkhoa.travellak.Entity.Cities;
import com.anhkhoa.travellak.Entity.DayTour;
import com.anhkhoa.travellak.Entity.TourImage;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

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

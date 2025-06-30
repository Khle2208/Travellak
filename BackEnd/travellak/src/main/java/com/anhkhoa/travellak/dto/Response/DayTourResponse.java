package com.anhkhoa.travellak.dto.Response;

import com.anhkhoa.travellak.Entity.Attractions;
import com.anhkhoa.travellak.Entity.Tour;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DayTourResponse {
    UUID dayId;
    int dayNumber;
    String title;
    String description;
    private Tour tour;
    private Attractions attraction;
}

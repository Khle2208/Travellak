package com.anhkhoa.travellak.dto.Request.DayTour;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DayTourCreationRequest {
    int dayNumber;
    UUID tourId;
    UUID attractionId;
    String title;
    String description;
}

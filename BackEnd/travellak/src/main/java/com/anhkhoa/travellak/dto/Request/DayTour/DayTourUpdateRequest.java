package com.anhkhoa.travellak.dto.Request.DayTour;

import java.util.UUID;

import lombok.Data;
@Data
public class DayTourUpdateRequest {
    private int dayNumber;
    private UUID tourId;
    private UUID attractionId;
    private String title;
    private String description;
}

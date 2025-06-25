package com.anhkhoa.travellak.dto.Request.Tour;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourUpdateRequest {
    String tourName;
    String description;
    int rating;
    double priceAdult;
    double priceChild;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate startDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate endDate;

    MultipartFile imageData;
    UUID departureCityId;
    UUID destinationCityId;
}

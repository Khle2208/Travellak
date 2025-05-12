package com.anhkhoa.travellak.dto.Request.Tour;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

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

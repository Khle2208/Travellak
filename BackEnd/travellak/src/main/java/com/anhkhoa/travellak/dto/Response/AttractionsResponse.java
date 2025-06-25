package com.anhkhoa.travellak.dto.Response;

import java.util.UUID;

import com.anhkhoa.travellak.Entity.Cities;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttractionsResponse {
    UUID attractionId;
    String attractionsName;
    String description;
    int rating;
    byte[] imageData;
    Cities city;
}

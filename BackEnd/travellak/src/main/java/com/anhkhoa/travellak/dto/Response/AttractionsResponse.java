package com.anhkhoa.travellak.dto.Response;

import com.anhkhoa.travellak.Entity.Cities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

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

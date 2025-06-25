package com.anhkhoa.travellak.dto.Request.Attractions;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.*;
import lombok.experimental.FieldDefaults;

// @Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttractionsCreationRequest {
    String attractionsName;
    String description;
    int rating;
    MultipartFile imageData;
    UUID cityId;
}

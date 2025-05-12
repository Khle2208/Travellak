package com.anhkhoa.travellak.dto.Request.Attractions;
import lombok.*;

import java.util.UUID;

import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

//@Data
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

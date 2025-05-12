package com.anhkhoa.travellak.dto.Request.TourImage;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourImageUpdateRequest {
    MultipartFile image;
    UUID tourId;
}

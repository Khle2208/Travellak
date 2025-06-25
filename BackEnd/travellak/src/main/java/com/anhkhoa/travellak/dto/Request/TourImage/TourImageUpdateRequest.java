package com.anhkhoa.travellak.dto.Request.TourImage;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourImageUpdateRequest {
    MultipartFile image;
    UUID tourId;
}

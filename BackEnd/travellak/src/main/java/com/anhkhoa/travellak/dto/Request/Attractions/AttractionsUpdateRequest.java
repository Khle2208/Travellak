package com.anhkhoa.travellak.dto.Request.Attractions;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttractionsUpdateRequest {
    String attractionsName;
    String description;
    int rating;
    MultipartFile imageData; // ảnh lưu dưới dạng byte[]
    UUID cityId;
}

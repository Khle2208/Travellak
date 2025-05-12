package com.anhkhoa.travellak.dto.Request.Attractions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttractionsUpdateRequest {
    String attractionsName;
    String description;
    int rating;
    MultipartFile imageData;   // ảnh lưu dưới dạng byte[]
    UUID cityId;
}

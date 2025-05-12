package com.anhkhoa.travellak.dto.Request.Tour;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourCreationRequest {
    @NotBlank(message = "Vui lòng nhập tên tour")
    String tourName;
    String description;
    int rating = 0;
    //    @Positive(message = "Giá người lớn phải > 0")
    @Min(value = 0, message = "Giá người lớn > 0")
    double priceAdult;

    //    @Positive(message = "Giá người trẻ em > 0")
    @Min(value = 0, message = "Giá trẻ em > 0")
    double priceChild;
    //    @Positive(message = "Giá người trẻ nhỏ > 0")
    @Min(value = 0, message = "Giá trẻ nhỏ > 0")
    double priceBaby;
    @NotNull(message = "Vui lòng chọn ảnh")
    MultipartFile imageData;
    @NotNull(message = "Vui lòng nơi bắt đầu")
    UUID departureCityId;
    @NotNull(message = "Vui lòng nơi điểm đến")
    UUID destinationCityId;
}

package com.anhkhoa.travellak.Controller;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhkhoa.travellak.Service.TourService;
import com.anhkhoa.travellak.dto.Request.Tour.TourCreationRequest;
import com.anhkhoa.travellak.dto.Request.Tour.TourUpdateRequest;
import com.anhkhoa.travellak.dto.Response.ApiResponse;
import com.anhkhoa.travellak.dto.Response.TourResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("Tours")
@CrossOrigin(origins = "http://localhost:5173")
public class TourController {
    TourService tourService;

    @GetMapping("/{tourId}")
    public ApiResponse<TourResponse> getTourById(@PathVariable("tourId") UUID tourId) {
        return ApiResponse.<TourResponse>builder()
                .result(tourService.getTourById(tourId))
                .build();
    }

    @GetMapping
    public ApiResponse<List<TourResponse>> getAllTour() {
        return ApiResponse.<List<TourResponse>>builder()
                .result(tourService.getAllTour())
                .build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<TourResponse> createTour(@ModelAttribute @Valid TourCreationRequest request) {
        return ApiResponse.<TourResponse>builder()
                .result(tourService.createTour(request))
                .build();
    }

    @PutMapping(value = "/{tourId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<TourResponse> updateTour(
            @PathVariable("tourId") UUID tourId, @ModelAttribute TourUpdateRequest request) {
        return ApiResponse.<TourResponse>builder()
                .result(tourService.updateTour(tourId, request))
                .build();
    }

    @DeleteMapping("/{tourId}")
    public String deleteTour(@PathVariable("tourId") UUID tourId) {
        return tourService.deleteTour(tourId);
    }
}

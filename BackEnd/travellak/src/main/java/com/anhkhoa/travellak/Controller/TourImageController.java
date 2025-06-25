package com.anhkhoa.travellak.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhkhoa.travellak.Entity.TourImage;
import com.anhkhoa.travellak.Service.TourImageService;
import com.anhkhoa.travellak.dto.Request.TourImage.TourImageCreationRequest;
import com.anhkhoa.travellak.dto.Request.TourImage.TourImageUpdateRequest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("TourImage")
public class TourImageController {
    TourImageService tourImageService;

    @GetMapping("/{tourImageId}")
    public TourImage getTourImageById(@PathVariable("tourImageId") UUID tourImageId) {
        return tourImageService.getTourImageById(tourImageId);
    }

    @GetMapping
    public List<TourImage> getAllTourImage() {
        return tourImageService.getAllTourImage();
    }

    @GetMapping("TourId/{tourId}")
    public List<TourImage> getTourImageByTourId(@PathVariable("tourId") UUID tourId) {
        return tourImageService.getTourImageByTourId(tourId);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TourImage createTourImage(@ModelAttribute TourImageCreationRequest request) {
        return tourImageService.createTourImage(request);
    }

    @PutMapping(value = "/{tourImageId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TourImage updateTourImage(
            @PathVariable("tourImageId") UUID tourImageId, @ModelAttribute TourImageUpdateRequest request) {
        return tourImageService.updateTourImage(tourImageId, request);
    }

    @DeleteMapping("/{tourImageId}")
    public String deleteTourImage(@PathVariable("tourImageId") UUID tourImageId) {
        return tourImageService.deleteTourImage(tourImageId);
    }

    @DeleteMapping("TourId/{tourid}")
    public String deleteTourImageByTourId(@PathVariable("tourId") UUID tourId) {
        return tourImageService.deleteTourImagebyTourId(tourId);
    }
}

package com.anhkhoa.travellak.Service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import com.anhkhoa.travellak.Mapper.TourImageMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.anhkhoa.travellak.Entity.Tour;
import com.anhkhoa.travellak.Entity.TourImage;
import com.anhkhoa.travellak.Repository.TourImageRepository;
import com.anhkhoa.travellak.Repository.TourRepository;
import com.anhkhoa.travellak.dto.Request.TourImage.TourImageCreationRequest;
import com.anhkhoa.travellak.dto.Request.TourImage.TourImageUpdateRequest;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourImageService {
    TourImageRepository tourImageRepository;

    TourRepository tourRepository;

    TourImageMapper tourImageMapper;

    public TourImage getTourImageById(UUID tourImageId) {
        return tourImageRepository.findById(tourImageId).orElseThrow(() -> new RuntimeException("Không có hình ảnh"));
    }

    public List<TourImage> getAllTourImage() {
        return tourImageRepository.findAll();
    }

    public List<TourImage> getTourImageByTourId(UUID tourId) {
        return tourImageRepository.findByTour_TourId(tourId);
    }

    public TourImage createTourImage(TourImageCreationRequest request) {
        Tour tour = tourRepository.findById(request.getTourId()).
                orElseThrow(() -> new RuntimeException("Không có tour"));
        MultipartFile imageFile = request.getImage();
        TourImage tourImage = tourImageMapper.toTourImage(request);
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                tourImage.setImage(imageFile.getBytes()); // Giả sử entity có field image: byte[]
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image", e);
            }
        }

        tourImage.setTour(tour);
        return tourImageRepository.save(tourImage);
    }

    public TourImage updateTourImage(UUID tourImageId, TourImageUpdateRequest request) {
        TourImage tourImage = getTourImageById(tourImageId);
        Tour tour = tourRepository.findById(request.getTourId()).
                orElseThrow(() -> new RuntimeException("Không có tour"));
        tourImageMapper.updateTourImage(tourImage, request);
        MultipartFile imageFile = request.getImage();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                tourImage.setImage(imageFile.getBytes()); // Giả sử entity có field image: byte[]
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image", e);
            }
        }

        tourImage.setTour(tour);
        return tourImageRepository.save(tourImage);
    }

    public String deleteTourImage(UUID tourImageId) {
        TourImage tourImage = getTourImageById(tourImageId);
        tourImageRepository.delete(tourImage);
        return "Xoá thành công";
    }

    public String deleteTourImagebyTourId(UUID tourId) {
        if (!tourRepository.existsById(tourId)) {
            return "Không có tour";
        }
        tourImageRepository.deleteByTour_TourId(tourId);
        return "Xoá thành công";
    }
}

package com.anhkhoa.travellak.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.anhkhoa.travellak.Mapper.TourMapper;
import com.anhkhoa.travellak.dto.Response.TourResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.anhkhoa.travellak.Entity.Cities;
import com.anhkhoa.travellak.Entity.Tour;
import com.anhkhoa.travellak.Repository.CitiesRepository;
import com.anhkhoa.travellak.Repository.TourRepository;
import com.anhkhoa.travellak.dto.Request.Tour.TourCreationRequest;
import com.anhkhoa.travellak.dto.Request.Tour.TourUpdateRequest;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourService {
    TourRepository tourRepository;

    CitiesRepository citiesRepository;

    TourMapper tourMapper;

    public TourResponse getTourById (UUID tourId){
        return tourMapper.tourTourResponse(tourRepository.findById(tourId).orElseThrow(() -> new RuntimeException("Tour không tồn tại")));
    }


    public List<TourResponse> getAllTour(){
        return tourMapper.toListTourResponses(tourRepository.findAll());
    }

    public TourResponse createTour(TourCreationRequest request){

        Cities departureCity = citiesRepository.findById(request.getDepartureCityId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thành phố"));
        Cities destinationCity = citiesRepository.findById(request.getDestinationCityId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thành phố"));

        Tour tour = tourMapper.toTour(request);

        MultipartFile imageFile = request.getImageData();


        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                tour.setImageData(imageFile.getBytes()); // Giả sử entity có field image: byte[]
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image", e);
            }
        }

        tour.setDepartureCity(departureCity);
        tour.setDestinationCity(destinationCity);

        if (tour.getDayTours() == null) {
            tour.setDayTours(new ArrayList<>());
        }
       
        return tourMapper.tourTourResponse(tourRepository.save(tour));
    }

    public TourResponse updateTour(UUID tourId,TourUpdateRequest request){
        Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new RuntimeException("Tour không tồn tại"));
        Cities departureCity = citiesRepository.findById(request.getDepartureCityId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thành phố"));
        Cities destinationCity = citiesRepository.findById(request.getDestinationCityId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thành phố"));

        tourMapper.updateTour(tour, request);

        MultipartFile imageFile = request.getImageData();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                tour.setImageData(imageFile.getBytes()); // Giả sử entity có field image: byte[]
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image", e);
            }
        }

        tour.setDepartureCity(departureCity);
        tour.setDestinationCity(destinationCity);
        return tourMapper.tourTourResponse(tourRepository.save(tour));
    }
    @PreAuthorize("true")
    public String deleteTour(UUID tourId){
        if (!tourRepository.existsById(tourId)){
            throw new RuntimeException("Không tìm thấy tour để xoá");
        } 
        tourRepository.deleteById(tourId);
        return "Xoá thành công";
    }

}

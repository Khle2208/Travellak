package com.anhkhoa.travellak.Service;

import java.util.List;
import java.util.UUID;

import com.anhkhoa.travellak.dto.Response.DayTourResponse;
import org.springframework.stereotype.Service;

import com.anhkhoa.travellak.Entity.Attractions;
import com.anhkhoa.travellak.Entity.DayTour;
import com.anhkhoa.travellak.Entity.Tour;
import com.anhkhoa.travellak.Mapper.DayTourMapper;
import com.anhkhoa.travellak.Repository.AttractionsRepository;
import com.anhkhoa.travellak.Repository.DayTourRepository;
import com.anhkhoa.travellak.Repository.TourRepository;
import com.anhkhoa.travellak.dto.Request.DayTour.DayTourCreationRequest;
import com.anhkhoa.travellak.dto.Request.DayTour.DayTourUpdateRequest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DayTourService {
    DayTourRepository dayTourRepository;

    TourRepository tourRepository;

    AttractionsRepository attractionsRepository;

    DayTourMapper dayTourMapper;

    public DayTourResponse getDayTourById(UUID dayTourId) {
        return dayTourMapper.toDayTourResponse(dayTourRepository.findById(dayTourId).orElseThrow(() -> new RuntimeException("Không có day tour")));
    }

    public List<DayTourResponse> getDayTourByTourId(UUID tourId) {
        return dayTourMapper.toListDayTourResponse(dayTourRepository.findByTour_TourId(tourId));
    }

    public List<DayTourResponse> getAllDayTour() {
        return dayTourMapper.toListDayTourResponse(dayTourRepository.findAll());
    }

    public DayTourResponse createDayTour(DayTourCreationRequest request) {
        Attractions attraction = attractionsRepository
                .findById(request.getAttractionId())
                .orElseThrow(() -> new RuntimeException("Không có đia điểm"));

        Tour tour =
                tourRepository.findById(request.getTourId()).orElseThrow(() -> new RuntimeException("Không có tour"));
        DayTour day = dayTourMapper.toDayTour(request);
        day.setTour(tour);
        day.setAttraction(attraction);
        return dayTourMapper.toDayTourResponse(dayTourRepository.save(day));

    }

    public DayTourResponse updateDayTour(UUID dayTourId, DayTourUpdateRequest request) {
        DayTour day = dayTourRepository.findById(dayTourId).orElseThrow(() -> new RuntimeException("Không có day tour"));

        Attractions attraction = attractionsRepository
                .findById(request.getAttractionId())
                .orElseThrow(() -> new RuntimeException("Không có đia điểm"));
        Tour tour =
                tourRepository.findById(request.getTourId()).orElseThrow(() -> new RuntimeException("Không có tour"));
        dayTourMapper.updateDayTour(day, request);
        day.setTour(tour);
        day.setAttraction(attraction);
        return dayTourMapper.toDayTourResponse(dayTourRepository.save(day));
    }

    public String deleteDayTour(UUID dayTourId) {
        DayTour day = dayTourRepository.findById(dayTourId).orElseThrow(() -> new RuntimeException("Không có day tour"));
        dayTourRepository.delete(day);
        return "Xoá thành công";
    }
}

package com.anhkhoa.travellak.Controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.anhkhoa.travellak.Entity.DayTour;
import com.anhkhoa.travellak.Service.DayTourService;
import com.anhkhoa.travellak.dto.Request.DayTour.DayTourCreationRequest;
import com.anhkhoa.travellak.dto.Request.DayTour.DayTourUpdateRequest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("DayTour")
@CrossOrigin(origins = "http://localhost:5173")
public class DayTourController {
    DayTourService dayTourService;

    @GetMapping("/{dayTourId}")
    public DayTour getDayTourById(@PathVariable("dayTourId") UUID dayTourId) {
        return dayTourService.getDayTourById(dayTourId);
    }

    @GetMapping
    public List<DayTour> getAllDayTour() {
        return dayTourService.getAllDayTour();
    }

    @PostMapping
    public DayTour createDayTour(@RequestBody DayTourCreationRequest request) {
        return dayTourService.createDayTour(request);
    }

    @PutMapping("/{dayTourId}")
    public DayTour updateDayTour(@PathVariable("dayTourId") UUID dayTourId, @RequestBody DayTourUpdateRequest request) {
        return dayTourService.updateDayTour(dayTourId, request);
    }

    @DeleteMapping("/{dayTourId}")
    public String deleteDayTour(@PathVariable("dayTourId") UUID dayTourId) {
        return dayTourService.deleteDayTour(dayTourId);
    }

    @PostMapping("AddListDayTour")
    public List<DayTour> createDayTours(@RequestBody List<DayTourCreationRequest> requests) {
        return requests.stream().map(dayTourService::createDayTour).collect(Collectors.toList());
    }
}

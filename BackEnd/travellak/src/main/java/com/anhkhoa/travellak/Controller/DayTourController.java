package com.anhkhoa.travellak.Controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.anhkhoa.travellak.dto.Response.ApiResponse;
import com.anhkhoa.travellak.dto.Response.DayTourResponse;
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
    public ApiResponse<DayTourResponse> getDayTourById(@PathVariable("dayTourId") UUID dayTourId) {
        return ApiResponse.<DayTourResponse>builder().result(dayTourService.getDayTourById(dayTourId)).build();
    }

    @GetMapping("/ByTour/{tourId}")
    public ApiResponse<List<DayTourResponse>> getDayTourByTourId(@PathVariable("tourId") UUID tourId){
        return ApiResponse.<List<DayTourResponse>>builder().result(dayTourService.getDayTourByTourId(tourId)).build();
    }
    @GetMapping
    public ApiResponse<List<DayTourResponse>> getAllDayTour() {
        return ApiResponse.<List<DayTourResponse>>builder().result(dayTourService.getAllDayTour()).build();
    }

    @PostMapping
    public ApiResponse<DayTourResponse> createDayTour(@RequestBody DayTourCreationRequest request) {
        return ApiResponse.<DayTourResponse>builder().result(dayTourService.createDayTour(request)).build();

    }

    @PutMapping("/{dayTourId}")
    public ApiResponse<DayTourResponse> updateDayTour(@PathVariable("dayTourId") UUID dayTourId, @RequestBody DayTourUpdateRequest request) {
        return ApiResponse.<DayTourResponse>builder().result(dayTourService.updateDayTour(dayTourId, request)).build();

    }

    @DeleteMapping("/{dayTourId}")
    public String deleteDayTour(@PathVariable("dayTourId") UUID dayTourId) {
        return dayTourService.deleteDayTour(dayTourId);
    }

    @PostMapping("AddListDayTour")
    public ApiResponse<List<DayTourResponse>> createDayTours(@RequestBody List<DayTourCreationRequest> requests) {
        return ApiResponse.<List<DayTourResponse>>builder().result(requests.stream().map(dayTourService::createDayTour).collect(Collectors.toList())).build();
    }
}

package com.anhkhoa.travellak.Mapper;
import com.anhkhoa.travellak.Entity.Tour;
import com.anhkhoa.travellak.dto.Request.Tour.TourCreationRequest;
import com.anhkhoa.travellak.dto.Request.Tour.TourUpdateRequest;
import com.anhkhoa.travellak.dto.Response.TourResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TourMapper {

    @Mapping(target = "departureCity", ignore = true)
    @Mapping(target = "destinationCity", ignore = true)
    @Mapping(target = "imageData", ignore = true)
    @Mapping(target = "dayTours", expression = "java(new java.util.ArrayList<>())")
    Tour toTour(TourCreationRequest request);

    @Mapping(target = "departureCity", ignore = true)
    @Mapping(target = "destinationCity", ignore = true)
    @Mapping(target = "imageData", ignore = true)
    void updateTour(@MappingTarget Tour tour, TourUpdateRequest request);

    TourResponse tourTourResponse(Tour tour);
    List<TourResponse> toListTourResponses(List<Tour> tours);

}

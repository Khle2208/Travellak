package com.anhkhoa.travellak.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.anhkhoa.travellak.Entity.DayTour;
import com.anhkhoa.travellak.dto.Request.DayTour.DayTourCreationRequest;
import com.anhkhoa.travellak.dto.Request.DayTour.DayTourUpdateRequest;

@Mapper(componentModel = "spring")
public interface DayTourMapper {
    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "attraction", ignore = true)
    DayTour toDayTour(DayTourCreationRequest request);

    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "attraction", ignore = true)
    void updateDayTour(@MappingTarget DayTour dayTour, DayTourUpdateRequest request);
}

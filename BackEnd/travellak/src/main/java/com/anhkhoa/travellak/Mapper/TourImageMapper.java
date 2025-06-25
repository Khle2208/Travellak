package com.anhkhoa.travellak.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.anhkhoa.travellak.Entity.TourImage;
import com.anhkhoa.travellak.dto.Request.TourImage.TourImageCreationRequest;
import com.anhkhoa.travellak.dto.Request.TourImage.TourImageUpdateRequest;

@Mapper(componentModel = "spring")
public interface TourImageMapper {

    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "image", ignore = true)
    TourImage toTourImage(TourImageCreationRequest request);

    @Mapping(target = "tour", ignore = true)
    @Mapping(target = "image", ignore = true)
    void updateTourImage(@MappingTarget TourImage tourImage, TourImageUpdateRequest request);
}

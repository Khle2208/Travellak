package com.anhkhoa.travellak.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.anhkhoa.travellak.Entity.Cities;
import com.anhkhoa.travellak.dto.Request.Cities.CitiesCreationRequest;
import com.anhkhoa.travellak.dto.Request.Cities.CitiesUpdateRequest;

@Mapper(componentModel = "spring")
public interface CitiesMapper {
    @Mapping(target = "country", ignore = true)
    Cities toCities(CitiesCreationRequest request);

    @Mapping(target = "country", ignore = true)
    void updateCities(@MappingTarget Cities cities, CitiesUpdateRequest request);
}

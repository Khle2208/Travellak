package com.anhkhoa.travellak.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.anhkhoa.travellak.Entity.Attractions;
import com.anhkhoa.travellak.dto.Request.Attractions.AttractionsCreationRequest;
import com.anhkhoa.travellak.dto.Request.Attractions.AttractionsUpdateRequest;
import com.anhkhoa.travellak.dto.Response.AttractionsResponse;

@Mapper(componentModel = "spring")
public interface AttractionsMapper {

    @Mapping(target = "imageData", ignore = true)
    @Mapping(target = "city", ignore = true)
    Attractions toAttractions(AttractionsCreationRequest request);

    @Mapping(target = "imageData", ignore = true)
    @Mapping(target = "city", ignore = true)
    void updateAttractions(@MappingTarget Attractions attractions, AttractionsUpdateRequest request);

    AttractionsResponse toAttractionsResponse(Attractions attraction);

    List<AttractionsResponse> toListAttractionsResponse(List<Attractions> attractions);
}

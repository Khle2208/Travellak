package com.anhkhoa.travellak.Mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.anhkhoa.travellak.Entity.Countries;
import com.anhkhoa.travellak.dto.Request.Countries.CountriesCreationRequest;
import com.anhkhoa.travellak.dto.Request.Countries.CountriesUpdateRequest;
import com.anhkhoa.travellak.dto.Response.CountriesResponse;

@Mapper(componentModel = "spring")
public interface CountriesMapper {

    Countries toCountries(CountriesCreationRequest request);

    void updateCountries(@MappingTarget Countries countries, CountriesUpdateRequest request);

    CountriesResponse toCountriesResponse(Countries countries);

    List<CountriesResponse> toListCountryResponses(List<Countries> coutries);
}

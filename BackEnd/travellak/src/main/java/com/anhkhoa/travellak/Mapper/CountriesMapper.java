package com.anhkhoa.travellak.Mapper;

import com.anhkhoa.travellak.Entity.Countries;
import com.anhkhoa.travellak.dto.Request.Countries.CountriesCreationRequest;
import com.anhkhoa.travellak.dto.Request.Countries.CountriesUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CountriesMapper {

    Countries toCountries(CountriesCreationRequest request);
    void updateCountries(@MappingTarget Countries countries, CountriesUpdateRequest request);
}

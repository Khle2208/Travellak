package com.anhkhoa.travellak.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.anhkhoa.travellak.Entity.Cities;
import com.anhkhoa.travellak.Entity.Countries;
import com.anhkhoa.travellak.Mapper.CitiesMapper;
import com.anhkhoa.travellak.Repository.CitiesRepository;
import com.anhkhoa.travellak.Repository.CountriesRepository;
import com.anhkhoa.travellak.dto.Request.Cities.CitiesCreationRequest;
import com.anhkhoa.travellak.dto.Request.Cities.CitiesUpdateRequest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CitiesService {
    CitiesRepository citiesRepository;

    CountriesRepository countriesRepository;

    CitiesMapper citiesMapper;

    public Cities createCity(CitiesCreationRequest request) {
        Countries country = countriesRepository
                .findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy quốc gia"));

        Cities city = citiesMapper.toCities(request);
        city.setCountry(country);

        return citiesRepository.save(city);
    }

    public List<Cities> getAllCities() {
        return citiesRepository.findAll();
    }

    public Cities getCityById(UUID id) {
        return citiesRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thành phố với ID: " + id));
    }

    public Cities updateCity(UUID id, CitiesUpdateRequest request) {
        Cities existingCity =
                citiesRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy thành phố"));

        BeanUtils.copyProperties(request, existingCity);

        Countries country = countriesRepository
                .findById(request.getCountryId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy quốc gia"));
        existingCity.setCountry(country);

        return citiesRepository.save(existingCity);
    }

    // Delete
    public String deleteCity(UUID id) {
        if (!citiesRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy thành phố để xoá");
        }
        citiesRepository.deleteById(id);
        return "Xoá thành công";
    }
}

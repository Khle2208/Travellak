package com.anhkhoa.travellak.Service;

import com.anhkhoa.travellak.Entity.Countries;
import com.anhkhoa.travellak.Mapper.CountriesMapper;
import com.anhkhoa.travellak.Repository.CountriesRepository;
import com.anhkhoa.travellak.dto.Request.Countries.CountriesUpdateRequest;
import com.anhkhoa.travellak.dto.Request.Countries.CountriesCreationRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CountriesService {
    CountriesRepository countriesRepository;

    @Autowired
    CountriesMapper countriesMapper;

    public Countries createCountry(CountriesCreationRequest request) {
        Countries country = countriesMapper.toCountries(request);
        return countriesRepository.save(country);
    }

    public List<Countries> getAllCountries() {
        return countriesRepository.findAll();
    }

    // Read one by ID
    public Countries getCountryById(UUID id) {
        return countriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy quốc gia với ID: " + id));
    }

    // Update
    public Countries updateCountry(UUID id, CountriesUpdateRequest request) {
        Countries countries = countriesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy quốc gia để cập nhật"));
        countriesMapper.updateCountries(countries, request);
        return countriesRepository.save(countries);
    }

    // Delete
    public String deleteCountry(UUID id) {
        if (!countriesRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy quốc gia để xoá");
        }
        countriesRepository.deleteById(id);
        return "Xoá thành công";
    }

}

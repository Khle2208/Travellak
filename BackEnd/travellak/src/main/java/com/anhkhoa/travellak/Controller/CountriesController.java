package com.anhkhoa.travellak.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;

import com.anhkhoa.travellak.Entity.Countries;
import com.anhkhoa.travellak.Service.CountriesService;
import com.anhkhoa.travellak.dto.Request.Countries.CountriesCreationRequest;
import com.anhkhoa.travellak.dto.Request.Countries.CountriesUpdateRequest;
import com.anhkhoa.travellak.dto.Response.ApiResponse;
import com.anhkhoa.travellak.dto.Response.CountriesResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("Countries")
public class CountriesController {
    CountriesService countriesService;

    @PostMapping
    public ApiResponse<CountriesResponse> createCountry(@RequestBody CountriesCreationRequest request) {
        //        return
        // ApiResponse.<AttractionsResponse>builder().result(attractionsService.createAttraction(request)).build();
        //        return countriesService.createCountry(request);
        return ApiResponse.<CountriesResponse>builder()
                .result(countriesService.createCountry(request))
                .build();
    }

    @GetMapping
    public List<Countries> getCountries() {
        return countriesService.getAllCountries();
    }

    @GetMapping("/{countryId}")
    public Countries getCountryById(@PathVariable("countryId") UUID countryId) {
        return countriesService.getCountryById(countryId);
    }

    @PutMapping("/{countryId}")
    public Countries updateCountries(
            @PathVariable("countryId") UUID countryId, @RequestBody CountriesUpdateRequest request) {
        return countriesService.updateCountry(countryId, request);
    }

    @DeleteMapping("/{countryId}")
    public String deleteCountry(@PathVariable("countryId") UUID countryId) {
        return countriesService.deleteCountry(countryId);
    }
}

package com.anhkhoa.travellak.Controller;

import com.anhkhoa.travellak.Entity.Countries;
import com.anhkhoa.travellak.Service.CountriesService;
import com.anhkhoa.travellak.dto.Request.Countries.CountriesCreationRequest;
import com.anhkhoa.travellak.dto.Request.Countries.CountriesUpdateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("Countries")
public class CountriesController {
    CountriesService countriesService;

    @PostMapping
    public Countries createService(@RequestBody CountriesCreationRequest request){
        return countriesService.createCountry(request);
    }

    @GetMapping
    public List<Countries> getCountries(){
        return countriesService.getAllCountries();
    }
    @GetMapping("/{countryId}")
    public Countries getCountryById(@PathVariable("countryId") UUID countryId){
        return countriesService.getCountryById(countryId);
    }

    @PutMapping("/{countryId}")
    public Countries updateCountries(@PathVariable("countryId") UUID countryId, @RequestBody CountriesUpdateRequest request){
        return countriesService.updateCountry(countryId, request);
    }

    @DeleteMapping("/{countryId}")
    public String deleteCountry(@PathVariable("countryId") UUID countryId){
        return countriesService.deleteCountry(countryId);
    }
}

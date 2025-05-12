package com.anhkhoa.travellak.Controller;

import com.anhkhoa.travellak.Entity.Cities;
import com.anhkhoa.travellak.Service.CitiesService;
import com.anhkhoa.travellak.dto.Request.Cities.CitiesCreationRequest;
import com.anhkhoa.travellak.dto.Request.Cities.CitiesUpdateRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("Cities")
public class CitiesController {

    CitiesService citiesService;

    @PostMapping
    public Cities createCity(@RequestBody CitiesCreationRequest request){
        return citiesService.createCity(request);
    }

    @GetMapping
    public List<Cities> getCities(){
        return citiesService.getAllCities();
    }

    @GetMapping("/{cityId}")
    public Cities getCityById(@PathVariable("cityId") UUID cityId){
        return citiesService.getCityById(cityId);
    }

    @PutMapping("/{cityId}")
    public Cities updateCity(@PathVariable("cityId") UUID cityId, @RequestBody CitiesUpdateRequest request){
        return citiesService.updateCity(cityId, request);
    }

    @DeleteMapping("/{cityId}")
    public String deleteCity(@PathVariable("cityId") UUID cityId){
        return citiesService.deleteCity(cityId);
    }
}

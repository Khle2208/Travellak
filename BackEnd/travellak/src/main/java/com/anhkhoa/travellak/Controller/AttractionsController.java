package com.anhkhoa.travellak.Controller;

import com.anhkhoa.travellak.Service.AttractionsService;
import com.anhkhoa.travellak.dto.Request.Attractions.AttractionsCreationRequest;
import com.anhkhoa.travellak.dto.Request.Attractions.AttractionsUpdateRequest;
import com.anhkhoa.travellak.dto.Response.ApiResponse;
import com.anhkhoa.travellak.dto.Response.AttractionsResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("Attractions")
@CrossOrigin(origins = "http://localhost:5173")
public class AttractionsController {

    AttractionsService attractionsService;

   
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<AttractionsResponse> createAttraction(@ModelAttribute AttractionsCreationRequest request){
        return ApiResponse.<AttractionsResponse>builder().result(attractionsService.createAttraction(request)).build();

    }

    @GetMapping
    public ApiResponse<List<AttractionsResponse>>  getAllAttractions(){
        return ApiResponse.<List<AttractionsResponse>>builder().result(attractionsService.getAllAttractions()).build();

    }

    @GetMapping("/{attractionId}")
    public ApiResponse<AttractionsResponse> getAttractionById(@PathVariable("attractionId") UUID attractionId){
        return ApiResponse.<AttractionsResponse>builder().result(attractionsService.getAttractionById(attractionId)).build();

    }

    @PutMapping(value = "/{attractionId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<AttractionsResponse> updateAttraction(@PathVariable("attractionId") UUID attractionId, @ModelAttribute AttractionsUpdateRequest request){
        return ApiResponse.<AttractionsResponse>builder().result(attractionsService.updateAttraction(attractionId, request)).build();

    }

    @DeleteMapping("/{attractionId}")
    public String deleteAttraction(@PathVariable("attractionId") UUID attractionId){
        return attractionsService.deleteAttraction(attractionId);
    }
}

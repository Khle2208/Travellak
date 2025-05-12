package com.anhkhoa.travellak.Service;

import com.anhkhoa.travellak.Entity.Attractions;
import com.anhkhoa.travellak.Entity.Cities;
import com.anhkhoa.travellak.Mapper.AttractionsMapper;
import com.anhkhoa.travellak.Repository.AttractionsRepository;
import com.anhkhoa.travellak.Repository.CitiesRepository;
import com.anhkhoa.travellak.dto.Request.Attractions.AttractionsCreationRequest;
import com.anhkhoa.travellak.dto.Request.Attractions.AttractionsUpdateRequest;
import com.anhkhoa.travellak.dto.Response.AttractionsResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor //tạo constructor chứa tất cả các biến được khai báo final và tự động inject các dependency
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // khai báo phạm vi là private và final
public class AttractionsService {

    AttractionsRepository attractionsRepository;

    CitiesRepository citiesRepository;

    AttractionsMapper attractionsMapper;

    // Create
    public AttractionsResponse createAttraction(AttractionsCreationRequest request) {

        Cities city = citiesRepository.findById(request.getCityId())
                .orElseThrow(() -> new RuntimeException("City not found"));
        Attractions attraction = attractionsMapper.toAttractions(request);
        attraction.setCity(city);
        // Tự xử lý image nếu cần (vì imageData là MultipartFile, còn trong entity có thể là byte[])
        MultipartFile imageFile = request.getImageData();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                attraction.setImageData(imageFile.getBytes()); // Giả sử entity có field image: byte[]
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image", e);
            }
        }

        return attractionsMapper.toAttractionsResponse(attractionsRepository.save(attraction));
    }

    // Get all
    public List<AttractionsResponse> getAllAttractions() {
        return attractionsMapper.toListAttractionsResponse(attractionsRepository.findAll());
    }

    // Get by ID
    public AttractionsResponse getAttractionById(UUID id) {
        return attractionsMapper.toAttractionsResponse(attractionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa điểm với ID: " + id)));
    }

    // Update
    public AttractionsResponse updateAttraction(UUID id, AttractionsUpdateRequest request) {
        Attractions attractions = attractionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy địa điểm"));

        Cities city = citiesRepository.findById(request.getCityId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thành phố"));
        attractionsMapper.updateAttractions(attractions, request);
        attractions.setCity(city);

        return attractionsMapper.toAttractionsResponse(attractionsRepository.save(attractions));
    }

    public String deleteAttraction(UUID id) {
        if (!attractionsRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy địa điểm để xoá");
        }
        attractionsRepository.deleteById(id);
        return "Xoá thành công";
    }
}

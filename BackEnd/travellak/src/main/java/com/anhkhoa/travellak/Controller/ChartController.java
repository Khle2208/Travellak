package com.anhkhoa.travellak.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anhkhoa.travellak.Service.ChartService;
import com.anhkhoa.travellak.dto.Response.ApiResponse;
import com.anhkhoa.travellak.dto.Response.ChartResponse;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("Admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true) // kha
public class ChartController {
    ChartService chartService;

    @GetMapping("/GetChart")
    public ApiResponse<ChartResponse> getChart() {
        System.out.println(chartService.getChart().getTotalRevenue());
        return ApiResponse.<ChartResponse>builder()
                .result(chartService.getChart())
                .build();
    }
}

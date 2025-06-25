package com.anhkhoa.travellak.Controller;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.anhkhoa.travellak.Service.CountriesService;
import com.anhkhoa.travellak.dto.Request.Countries.CountriesCreationRequest;
import com.anhkhoa.travellak.dto.Response.CountriesResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CountriesService countriesService;

    private CountriesCreationRequest request;
    private CountriesResponse countriesResponse;

    @BeforeEach
    void initData() {
        request = CountriesCreationRequest.builder()
                .countryName("Mỹ")
                .isoCode("UK")
                .build();
        countriesResponse = CountriesResponse.builder()
                .countryId(UUID.randomUUID())
                .countryName("Mỹ")
                .isoCode("UK")
                .build();
    }

    @Test
    void createCountry_validRequest_Success() throws Exception {
        //        Given
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(request);

        Mockito.when(countriesService.createCountry(ArgumentMatchers.any())).thenReturn(countriesResponse);
        //        When
        mockMvc.perform(MockMvcRequestBuilders.post("/Countries")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("1000"));
        //        Then
    }
}

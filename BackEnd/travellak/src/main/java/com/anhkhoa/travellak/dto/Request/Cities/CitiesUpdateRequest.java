package com.anhkhoa.travellak.dto.Request.Cities;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CitiesUpdateRequest {
    String cityName;
    UUID countryId;
}

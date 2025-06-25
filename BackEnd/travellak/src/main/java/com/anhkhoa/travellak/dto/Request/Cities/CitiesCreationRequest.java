package com.anhkhoa.travellak.dto.Request.Cities;

import java.util.UUID;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CitiesCreationRequest {
    String cityName;
    UUID countryId;
}

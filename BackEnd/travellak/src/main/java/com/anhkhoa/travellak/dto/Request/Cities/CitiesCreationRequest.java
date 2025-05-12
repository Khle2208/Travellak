package com.anhkhoa.travellak.dto.Request.Cities;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CitiesCreationRequest {
    String cityName;
    UUID countryId;
}

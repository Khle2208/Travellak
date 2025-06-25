package com.anhkhoa.travellak.dto.Response;

import java.util.UUID;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountriesResponse {
    UUID countryId;
    String countryName;
    String isoCode;
}

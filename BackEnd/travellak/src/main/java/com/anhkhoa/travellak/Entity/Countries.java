package com.anhkhoa.travellak.Entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Countries {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID countryId;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    String countryName;

    String isoCode;
}

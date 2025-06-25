package com.anhkhoa.travellak.Entity;

import java.util.UUID;

import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Attractions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID attractionId;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    String attractionsName;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    String description;

    int rating;

    @Lob
    @Column(columnDefinition = "VARBINARY(MAX)")
    byte[] imageData;

    @ManyToOne
    @JoinColumn(name = "city_id")
    Cities city;
}

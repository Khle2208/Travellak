package com.anhkhoa.travellak.Entity;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID tourId;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    String tourName;
    @Column(columnDefinition = "NVARCHAR(MAX)")
    String description;
    int rating;
    double priceAdult;
    double priceChild;
    double priceBaby;
    @Lob
    @Column(columnDefinition = "VARBINARY(MAX)")
    byte[] imageData;
    @ManyToOne
    @JoinColumn(name = "departure_city_id", nullable = false)
    Cities departureCity;
    @ManyToOne
    @JoinColumn(name = "destination_city_id", nullable = false)
    Cities destinationCity;
    
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<DayTour> dayTours;


    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TourImage> tourImages;
}

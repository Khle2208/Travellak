package com.anhkhoa.travellak.Entity;

import java.util.UUID;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class DayTour {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID dayId;

    int dayNumber;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    String title;

    @Column(columnDefinition = "NVARCHAR(MAX)")
    String description;

    @ManyToOne
    @JoinColumn(name = "tour_id")
    @JsonBackReference
    private Tour tour;

    @ManyToOne
    @JoinColumn(name = "attraction_id", nullable = false)
    private Attractions attraction;
}

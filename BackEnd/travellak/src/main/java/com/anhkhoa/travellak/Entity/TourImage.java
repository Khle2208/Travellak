package com.anhkhoa.travellak.Entity;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity 
public class TourImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID tourImageId;
    @Lob
    @Column(columnDefinition = "VARBINARY(MAX)")
    byte[] image;
    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    @JsonBackReference
    Tour tour;
}

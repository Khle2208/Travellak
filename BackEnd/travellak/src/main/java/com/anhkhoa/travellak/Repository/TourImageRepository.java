package com.anhkhoa.travellak.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhkhoa.travellak.Entity.TourImage;

@Repository
public interface TourImageRepository extends JpaRepository<TourImage, UUID> {
    List<TourImage> findByTour_TourId(UUID tourId);

    void deleteByTour_TourId(UUID tourId);
}

package com.anhkhoa.travellak.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhkhoa.travellak.Entity.DayTour;

@Repository
public interface DayTourRepository extends JpaRepository<DayTour, UUID> {
    List<DayTour> findByTour_TourId(UUID tourId);
}

package com.anhkhoa.travellak.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anhkhoa.travellak.Entity.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, UUID> {}
